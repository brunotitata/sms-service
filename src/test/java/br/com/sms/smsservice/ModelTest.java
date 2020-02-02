package br.com.sms.smsservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.sms.SmsRepository;

@SpringBootTest
public class ModelTest {

//    @Autowired
//    private EmailListenerService emailListenerService;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    public void hauehuea() {
//
//	emailListenerService.sendMail(new ExceptionCommand("",
//		"Caused by: javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at",
//		HttpStatus.BAD_REQUEST));
//    }
//
    
    @Autowired
    private SmsRepository smsRepository;
    
    @Test
    public void geayge() {
	
	SmsFilter smsFilter = new SmsFilter();
	smsFilter.setNameCustomer("drian");
	
	List<SMS> findSmsByFilter = smsRepository.findSmsByFilter(smsFilter);
	
	System.out.println(findSmsByFilter);

    }

}
