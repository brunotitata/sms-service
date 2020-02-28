package br.com.sms.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

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

    private UserRepository userRepository;
    private SmsRepository smsRepository;

    public UserServiceImpl(UserRepository userRepository, SmsRepository smsRepository) {
	this.userRepository = userRepository;
	this.smsRepository = smsRepository;
    }

    @Override
    public UserStatistics getUserStatistics(String userId) {

	long sevenDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(7).toString(), LocalDate.now().toString(), userId)))
		.stream().count();

	long fifteenDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(15).toString(), LocalDate.now().toString(), userId)))
		.stream().count();

	long thirtyDays = smsRepository
		.findAll(SmsSpecification.filter(
			new SmsFilter(LocalDate.now().minusDays(30).toString(), LocalDate.now().toString(), userId)))
		.stream().count();

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

}
