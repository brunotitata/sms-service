package br.com.sms.smsservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
import br.com.sms.repository.customer.CustomerSpecification;
import br.com.sms.repository.establishment.EstablishmentRepository;
import br.com.sms.repository.user.UserRepository;

@SpringBootTest
public class CustomerSpecificationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customers = Collections.emptySet();
	Set<Employee> employees = Collections.emptySet();

	Establishment estabelecimento = establishmentRepository.save(new Establishment(
		new EstablishmentId(UUID.fromString("0e488aac-1a68-412a-baad-e1319a49fd01")), "Arley Chopão",
		"Leopoldo Carlos de Oliveira 350", "06.100.428/0001-92", employees, sms, customers));

	userRepository.save(new User(new UserId(UUID.fromString("46c2c662-d900-4f4f-9520-036c0a7ce1e9")), "Arley",
		"16991034148", "384.418.688-32", "brunotitata@gmail.com", "123456", estabelecimento));

	User user = userRepository.findClientById(UUID.fromString("46c2c662-d900-4f4f-9520-036c0a7ce1e9"))
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

    @AfterEach
    public void tearDown() {
	userRepository.delete(UUID.fromString("46c2c662-d900-4f4f-9520-036c0a7ce1e9"));
    }

    @Test
    public void findAllCustomerByCpf() {

	List<Customer> customers = customerRepository.find(CustomerSpecification.findCustomerByCpf("384.418.688-32"));

	assertEquals(2, customers.size());
	assertEquals(UUID.fromString("cd754feb-aa23-489e-bfc2-20d1c4d82978"), customers.get(0).getCustomerId().getId());
	assertEquals(UUID.fromString("1d776c06-dfec-49cf-acf8-a48012ceaf15"), customers.get(1).getCustomerId().getId());
    }

    @Test
    public void findCustomerByCellphone() {

	List<Customer> customer = customerRepository
		.find(CustomerSpecification.findCustomerByCellphone("384.418.688-32", "2222222222"));

	assertEquals(UUID.fromString("cd754feb-aa23-489e-bfc2-20d1c4d82978"), customer.get(0).getCustomerId().getId());
	assertEquals("Borracheiro do João", customer.get(0).getName());
	assertEquals("borracheiro@loja.com", customer.get(0).getEmail());
	assertEquals("2222222222", customer.get(0).getCellPhone());
    }

    @Test
    public void findCustomerWithCpfInvalid() {

	List<Customer> customers = customerRepository
		.find(CustomerSpecification.findCustomerByCellphone("384.418.688-300", "2222222222"));

	assertEquals(0, customers.size());
    }

    @Test
    public void findCustomerWithCellphoneInvalid() {

	List<Customer> customers = customerRepository
		.find(CustomerSpecification.findCustomerByCellphone("384.418.688-32", "22222211122"));

	assertEquals(0, customers.size());
    }

}
