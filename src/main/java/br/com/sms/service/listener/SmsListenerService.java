package br.com.sms.service.listener;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.Customer;
import br.com.sms.model.SMS;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.service.SmsCommand;

@Component
public class SmsListenerService {

    private SmsRepository smsRepository;
    private UserRepository userRepository;
    private CustomerRepository customerRepository;

    public SmsListenerService(SmsRepository smsRepository, UserRepository userRepository,
	    CustomerRepository customerRepository) {
	this.smsRepository = smsRepository;
	this.userRepository = userRepository;
	this.customerRepository = customerRepository;
    }

    @EventListener
    public void processSMS(SmsCommand command) {

	userRepository.findById(command.getUser()).ifPresent(user -> {
	    user.setCredit(user.debitCredits());
	    user.setCounterSms(user.smsCounter());

	    userRepository.save(user);

	    Customer customer = customerRepository.findCellphone(command.getNumberPhone()).orElse(null);

	    smsRepository.save(new SMS(command.getNumberPhone(), command.getBody(), LocalDateTime.now(),
		    command.getStatus(), customer));
	});

    }

}
