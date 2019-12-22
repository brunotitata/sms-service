package br.com.sms.service.listener;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.SMS;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.service.impl.SmsCommand;

@Component
public class SmsListenerService {

    private SmsRepository smsRepository;
    private UserRepository userRepository;

    public SmsListenerService(SmsRepository smsRepository, UserRepository userRepository) {
	this.smsRepository = smsRepository;
	this.userRepository = userRepository;
    }

    @EventListener
    public void processSMS(SmsCommand command) {

	userRepository.findByIdUser(command.getUser()).ifPresent(user -> {
	    user.setCredit(user.debitCredits());

	    smsRepository.save(new SMS(command.getNumberPhone(), command.getBody(), command.getStatus(),
		    LocalDateTime.now(), userRepository.save(user)));
	});

    }

}
