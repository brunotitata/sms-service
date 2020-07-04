package br.com.sms.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import br.com.sms.dto.CreditDTO;
import br.com.sms.dto.UserDTO;
import br.com.sms.dto.UserDTO.EstablishmentDTO;
import br.com.sms.login.exception.UserNotFoundException;
import br.com.sms.model.User;
import br.com.sms.model.UserStatistics;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.repository.sms.SmsSpecification;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final SmsRepository smsRepository;

    public UserServiceImpl(UserRepository userRepository, SmsRepository smsRepository) {
	this.userRepository = userRepository;
	this.smsRepository = smsRepository;
    }

    @Override
    public UserStatistics getUserStatistics(String userId) {

	long sevenDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(7).toString(), LocalDate.now().toString(), userId)))
		.size();

	long fifteenDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(15).toString(), LocalDate.now().toString(), userId)))
		.size();

	long thirtyDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(30).toString(), LocalDate.now().toString(), userId)))
		.size();

	User user = userRepository.findUserByUserId(userId)
		.orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID: " + userId));

	return new UserStatistics(user.getCreditoDisponivel(), user.getQuantidadeTotalDeSmsEnviado(),
		Integer.valueOf(String.valueOf(sevenDays)), Integer.valueOf(String.valueOf(fifteenDays)),
		Integer.valueOf(String.valueOf(thirtyDays)));
    }

    @Override
    public String messagePrefix(String userId) {
	return userRepository.findUserByUserId(userId)
		.orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID: " + userId))
		.getMensagemPrefixo();
    }

    @Override
    public UserDTO user(String userId) {

	User user = userRepository.findUserByUserId(userId)
		.orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID: " + userId));

	return new UserDTO(user.getNome(), user.getCelular(), user.getCpf(), user.getEmail(),
		new EstablishmentDTO(user.getEstablishment().getNome(), user.getEstablishment().getEndereco(),
			user.getEstablishment().getCnpj()),
		user.getMensagemPrefixo());
    }

    @Override
    public void userEdit(String userId, UserDTO userDTO) {

	User user = userRepository.findUserByUserId(userId)
		.orElseThrow(() -> new UserNotFoundException("Usuario não encontrado com ID: " + userId));

	user.setNome(userDTO.getNome());
	user.setCelular(userDTO.getCelular());
	user.setMensagemPrefixo(userDTO.getMensagemPrefixo());

	userRepository.save(user);
    }

    @SqsListener(value = "sms-payment-send", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    @Override
    public void addCredit(CreditDTO creditoDTO) {

	LOG.info("------------------ Recebendo evento de recarga, usuario: " + creditoDTO.getUserId());

	User user = userRepository.findUserByUserId(creditoDTO.getUserId()).orElseThrow(
		() -> new UserNotFoundException("Usuario não encontrado com ID: " + creditoDTO.getUserId()));

	Integer creditoDisponivel = user.getCreditoDisponivel();
	user.setCreditoDisponivel(Integer.sum(creditoDisponivel, Integer.valueOf(creditoDTO.getQuantity())));
	userRepository.save(user);

	LOG.info("------------------ Recarga adicionado com sucesso! ");
    }

}
