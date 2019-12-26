package br.com.sms.smsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.service.ExceptionCommand;
import br.com.sms.service.listener.EmailListenerService;

@SpringBootTest
public class ModelTest {

    @Autowired
    private EmailListenerService emailListenerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void hauehuea() {

	emailListenerService.sendMail(new ExceptionCommand("",
		"Caused by: javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at",
		HttpStatus.BAD_REQUEST));
    }

    @Test
    public void geayge() {
	System.out.println(customerRepository.findCustomer("", "6765"));

    }

}
