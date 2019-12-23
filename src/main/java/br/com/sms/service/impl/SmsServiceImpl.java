package br.com.sms.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sms.DTO.SmsDTO;
import br.com.sms.login.exception.EmailNotFoundException;
import br.com.sms.login.exception.InsufficientCreditsException;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.service.SmsCommand;
import br.com.sms.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

    private RestTemplate restTemplate;
    private ApplicationEventPublisher applicationEventPublisher;
    private String keyApi;
    private UserRepository userRepository;

    public SmsServiceImpl(RestTemplate restTemplate, ApplicationEventPublisher applicationEventPublisher,
	    @Value("${smsdev.key.api}") String keyApi, UserRepository userRepository) {
	this.restTemplate = restTemplate;
	this.applicationEventPublisher = applicationEventPublisher;
	this.keyApi = keyApi;
	this.userRepository = userRepository;
    }

    @Override
    public void send(SmsDTO smsDTO) {

	User user = userRepository.findById(smsDTO.getUserId())
		.orElseThrow(() -> new EmailNotFoundException("User not found: " + smsDTO.getUserId()));

	if (user.existCredit())
	    throw new InsufficientCreditsException(
		    "Não há mais credito para enviar SMS. Por favor, entrar em contato com o administrador.");

//	ResponseEntity<String> response = restTemplate.exchange("https://api.smsdev.com.br/send?key=" + keyApi
//		+ "&type=9&number=" + smsDTO.getNumber() + "&msg=" + smsDTO.getBody(), HttpMethod.GET, null,
//		String.class);

//	applicationEventPublisher.publishEvent(new SmsCommand(smsDTO.getNumber(), smsDTO.getBody(),
//		response.getStatusCode().name(), smsDTO.getUserId()));

	applicationEventPublisher
		.publishEvent(new SmsCommand("+5516999999", "corpo da mensagem aqui", "OK", smsDTO.getUserId()));

    }

}
