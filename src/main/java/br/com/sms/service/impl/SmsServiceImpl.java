package br.com.sms.service.impl;

import br.com.sms.dto.SmsDTO;
import br.com.sms.dto.SmsIdDTO;
import br.com.sms.dto.SmsSpecificationDTO;
import br.com.sms.login.exception.BusinessLogicException;
import br.com.sms.login.exception.InsufficientCreditsException;
import br.com.sms.login.exception.UserNotFoundException;
import br.com.sms.login.util.Utils;
import br.com.sms.model.Customer;
import br.com.sms.model.SMS;
import br.com.sms.model.SmsId;
import br.com.sms.model.User;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.repository.sms.SmsSpecification;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.AwsService;
import br.com.sms.service.SmsCommand;
import br.com.sms.service.SmsService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.PublishResult;
import org.jboss.logging.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger log = Logger.getLogger(SmsServiceImpl.class);

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AwsService awsService;
    private final UserRepository userRepository;
    private final SmsRepository smsRepository;

    public SmsServiceImpl(ApplicationEventPublisher applicationEventPublisher, AwsService awsService,
                          UserRepository userRepository, SmsRepository smsRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.awsService = awsService;
        this.userRepository = userRepository;
        this.smsRepository = smsRepository;
    }

    @Override
    public void send(SmsDTO smsDTO) {

        User user = userRepository.findUserByUserId(smsDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID: " + smsDTO.getUserId()));

        if (user.isCreditoDisponivel()) {
            throw new InsufficientCreditsException("Saldo de creditos insuficiente.");
        }

        user.getEstablishment().getCustomer().stream()
                .filter(customer -> customer.getCellPhone().equals(smsDTO.getNumber()))
                .findFirst()
                .ifPresent(customer -> {

                    user.getEstablishment().getEmployee().stream()
                            .filter(employee -> employee.getNome().equals(smsDTO.getNameEmployee()))
                            .findFirst()
                            .ifPresent(employee -> {

                                try {
                                    PublishResult publishResult = awsService.sendSms(smsDTO.getNumber(),
                                            smsDTO.getMessageBody());

                                    applicationEventPublisher
                                            .publishEvent(new SmsCommand(smsDTO.getNumber(), smsDTO.getMessageBody(),
                                                    Utils.convertHttpStatus(
                                                            publishResult.getSdkHttpMetadata().getHttpStatusCode()),
                                                    publishResult.getMessageId(), smsDTO.getUserId(),
                                                    smsDTO.getNameEmployee(), null));

                                } catch (AmazonServiceException e) {

                                    applicationEventPublisher.publishEvent(new SmsCommand(smsDTO.getNumber(),
                                            smsDTO.getMessageBody(), Utils.convertHttpStatus(e.getStatusCode()), null,
                                            smsDTO.getUserId(), smsDTO.getNameEmployee(), e.getMessage()));
                                }
                            });
                });

    }

    @Override
    public List<SmsSpecificationDTO> smsReport(SmsFilter smsFilter) {
        return smsRepository.findAll(SmsSpecification.filter(smsFilter)).stream().map(SMS::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void sendAll(SmsDTO smsDTO) {

        User user = userRepository.findUserByUserId(smsDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID : " + smsDTO.getUserId()));

        if (smsDTO.getMessageBody().length() <= 20) {
            throw new BusinessLogicException("O corpo da mensagem não pode ter menos que 20 caracteres!");
        }

        user.getEstablishment().getCustomer().stream().map(Customer::getCellPhone).forEach(number -> {

            try {
                PublishResult publishResult = awsService.sendSms(number, smsDTO.getMessageBody());

                applicationEventPublisher.publishEvent(new SmsCommand(number, smsDTO.getMessageBody(),
                        Utils.convertHttpStatus(publishResult.getSdkHttpMetadata().getHttpStatusCode()),
                        publishResult.getMessageId(), smsDTO.getUserId(), smsDTO.getNameEmployee(), null));

            } catch (AmazonServiceException amazonException) {

                applicationEventPublisher.publishEvent(
                        new SmsCommand(number, smsDTO.getMessageBody(), Utils.convertHttpStatus(amazonException.getStatusCode()),
                                null, smsDTO.getUserId(), smsDTO.getNameEmployee(), amazonException.getMessage()));
            }

        });

    }

    @Override
    public void reSend(SmsIdDTO smsId) {

        final Optional<SMS> sms = smsRepository.findSmsWithSmsId(new SmsId(UUID.fromString(smsId.getSmsId())));

        if (sms.isPresent()) {
            SMS newSMS = sms.get();
            log.info("SMS encontrado com UUID: " + newSMS.getSmsId().getSmsId() + "... Reenviando!");
            try {
                final PublishResult publishResult = awsService.sendSms(newSMS.getNumero(), newSMS.getMessagem());
                newSMS.setAwsMessageId(publishResult.getMessageId());
                newSMS.setLocalDateTime(LocalDateTime.now());
                newSMS.setStatus(Utils.convertHttpStatus(publishResult.getSdkHttpMetadata().getHttpStatusCode()));
                smsRepository.save(newSMS);
                log.info("SMS reenviado com sucesso!");
            } catch (AmazonServiceException amazonException) {
                throw new BusinessLogicException("Houve um error ao reprocessar mensagem: " + amazonException.getMessage());
            }
        } else {
            throw new RuntimeException("Não encontrou SMS com UUID: " + smsId.getSmsId());
        }
    }

}
