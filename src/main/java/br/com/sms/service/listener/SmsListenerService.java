package br.com.sms.service.listener;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.sms.model.SMS;
import br.com.sms.model.SmsId;
import br.com.sms.model.User;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.employee.EmployeeRepository;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.SmsCommand;

@Component
public class SmsListenerService {

    private SmsRepository smsRepository;
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public SmsListenerService(SmsRepository smsRepository, UserRepository userRepository,
	    CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
	this.smsRepository = smsRepository;
	this.userRepository = userRepository;
	this.customerRepository = customerRepository;
	this.employeeRepository = employeeRepository;
    }

    @EventListener
    public void processSMS(SmsCommand command) {

	User user = userRepository.findByCpf(command.getUserCpf());
	user.setQuantidadeTotalDeSmsEnviado(user.smsCounter());
	user.setCreditoDisponivel(user.creditAvailable());

	user.getEstablishment().getEmployee().stream()
		.filter(employee -> employee.getNome().equals(command.getNameEmployee()))
		.findFirst()
		.ifPresent(employee -> {
		    employee.setQuantidadeDeSmsEnviado(employee.smsCounter());
		    employeeRepository.save(employee);
		});

	user.getEstablishment().getCustomer().stream()
		.filter(customer -> customer.getCellPhone().equals(command.getNumberPhone()))
		.findFirst()
		.ifPresent(customer -> {
		    customer.setQuantityOfSmsSent(customer.counterSms());
		    customerRepository.save(customer);
		});

	userRepository.save(user);

	smsRepository.save(new SMS(new SmsId(UUID.randomUUID()), command.getNameEmployee(), command.getBody(),
		command.getNumberPhone(), LocalDateTime.now(), command.getAwsMessageId(), command.getStatus(),
		command.getMessageError(), user.getEstablishment()));

    }

}
