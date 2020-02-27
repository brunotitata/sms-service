package br.com.sms.smsservice;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.Active;
import br.com.sms.model.Customer;
import br.com.sms.model.CustomerId;
import br.com.sms.model.Employee;
import br.com.sms.model.EmployeeId;
import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.model.UserId;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.establishment.EstablishmentRepository;
import br.com.sms.repository.user.UserRepository;

public class AbstractTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customers = Collections.emptySet();
	Set<Employee> employees = Collections.emptySet();

	Establishment estabelecimento = establishmentRepository.save(new Establishment(
		new EstablishmentId(UUID.fromString("0e488aac-1a68-412a-baad-e1319a49fd01")), "Arley Chopão",
		"Leopoldo Carlos de Oliveira 350", "06.100.428/0001-92", employees, sms, customers));

	userRepository.save(new User(new UserId(UUID.fromString("46c2c662-d900-4f4f-9520-036c0a7ce1e9")), "Arley",
		"16991034148", "384.418.688-32", "brunotitata@gmail.com", "123456", estabelecimento));

	User user = userRepository.findUserByUserId("46c2c662-d900-4f4f-9520-036c0a7ce1e9")
		.orElseThrow(() -> new ArgumentInvalidException(
			"Cliente não encontrado com ID: " + "ad416334-e578-4181-8c2f-75e1859a4f1e"));
	Establishment establishment = user.getEstablishment();

	establishment.setEmployee(Stream.of(
		new Employee(new EmployeeId(UUID.randomUUID()), "Rodrigo", "rodrigo@gmail.com", "123456", Active.ATIVO,
			establishment),
		new Employee(new EmployeeId(UUID.randomUUID()), "Leandro", "leandro@gmail.com", "123456", Active.ATIVO,
			establishment))
		.collect(Collectors.toSet()));

	customerRepository.saveAll(Stream
		.of(new Customer(new CustomerId(UUID.fromString("1d776c06-dfec-49cf-acf8-a48012ceaf15")),
			"Farmacia do Seizi", "12121212121", "seizi@farmacia.com", establishment),
			new Customer(new CustomerId(UUID.fromString("cd754feb-aa23-489e-bfc2-20d1c4d82978")),
				"Borracheiro do João", "2222222222", "borracheiro@loja.com", establishment))
		.collect(Collectors.toSet()));

	establishmentRepository.save(establishment);
	userRepository.save(user);
    }

}
