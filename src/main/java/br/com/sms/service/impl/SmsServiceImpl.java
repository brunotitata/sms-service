package br.com.sms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.PublishResult;

import br.com.sms.dto.SmsDTO;
import br.com.sms.dto.SmsSpecificationDTO;
import br.com.sms.login.exception.InsufficientCreditsException;
import br.com.sms.login.util.Utils;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.repository.sms.SmsSpecification;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.AwsService;
import br.com.sms.service.SmsCommand;
import br.com.sms.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

    private ApplicationEventPublisher applicationEventPublisher;
    private AwsService awsService;
    private UserRepository userRepository;
    private SmsRepository smsRepository;

    public SmsServiceImpl(ApplicationEventPublisher applicationEventPublisher, AwsService awsService,
	    UserRepository userRepository, SmsRepository smsRepository) {
	this.applicationEventPublisher = applicationEventPublisher;
	this.awsService = awsService;
	this.userRepository = userRepository;
	this.smsRepository = smsRepository;
    }

    @Override
    public void send(SmsDTO smsDTO) {

	User user = userRepository.findByCpf(smsDTO.getCpfUser());

	if (user.getCreditoDisponivel() <= 0)
	    throw new InsufficientCreditsException("Saldo de creditos insuficiente.");

	user.getEstablishment().getCustomer().stream()
		.filter(customer -> customer.getCellPhone().equals(smsDTO.getNumber())).findFirst()
		.ifPresent(customer -> {

		    user.getEstablishment().getEmployee().stream()
			    .filter(employee -> employee.getNome().equals(smsDTO.getNameEmployee())).findFirst()
			    .ifPresent(employee -> {

				try {
				    PublishResult publishResult = awsService.sendSms(smsDTO);

				    applicationEventPublisher
					    .publishEvent(new SmsCommand(smsDTO.getNumber(), smsDTO.getMessageBody(),
						    Utils.convertHttpStatus(
							    publishResult.getSdkHttpMetadata().getHttpStatusCode()),
						    publishResult.getMessageId(), smsDTO.getCpfUser(),
						    smsDTO.getNameEmployee(), null));
				} catch (AmazonServiceException e) {

				    applicationEventPublisher.publishEvent(new SmsCommand(smsDTO.getNumber(),
					    smsDTO.getMessageBody(), Utils.convertHttpStatus(e.getStatusCode()), null,
					    smsDTO.getCpfUser(), smsDTO.getNameEmployee(), e.getMessage()));
				}
			    });
		});

    }

    @Override
    public List<SmsSpecificationDTO> smsReport(SmsFilter smsFilter) {
	return smsRepository.findAll(SmsSpecification.filter(smsFilter)).stream().map(SMS::convertToDTO)
		.collect(Collectors.toList());
    }

}
