package br.com.sms.service.listener;

import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.sms.service.ExceptionCommand;

@Component
public class EmailListenerService {

    private JavaMailSender mailSender;

    public EmailListenerService(JavaMailSender mailSender) {
	this.mailSender = mailSender;
    }

    @EventListener
    public void sendMail(ExceptionCommand command) {
	SimpleMailMessage message = new SimpleMailMessage();

	message.setSubject("[ERROR] - API SMS");
	
	message.setText("Ocorreu um erro ao consumir a API de SMS... \n"
		+ command.getBody() + "\n"
		+ command.getMessage() + "\n" 
		+ command.getHttpStatus());

	message.setTo("brunotitata@gmail.com");
	message.setFrom("brunotitata2@gmail.com");

	mailSender.send(message);
    }

}
