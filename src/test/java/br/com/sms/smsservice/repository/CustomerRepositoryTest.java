package br.com.sms.smsservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.role.RoleRepository;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.Customer;
import br.com.sms.model.SMS;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.sms.SmsRepository;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User user;
    private Customer customer;

    @BeforeEach
    public void setUp() {

	user = userRepository.save(new User("Bruno", "Costa", "bruno123@bruno.com", "123456",
		Stream.of(roleRepository.save(new Role(RoleName.ROLE_USER))).collect(Collectors.toSet())));

	customer = customerRepository.save(new Customer("Tiago", "16992700000", "tiago123@gmail.com", user));

	smsRepository.saveAll(Arrays.asList(
		new SMS("11111111111", "messagem 1", LocalDateTime.parse("2020-02-01T16:25:21.000"), "OK", customer),
		new SMS("22222222222", "messagem 2", LocalDateTime.parse("2020-01-28T19:43:21.000"), "OK", customer),
		new SMS("33333333333", "messagem 3", LocalDateTime.parse("2020-01-23T23:01:21.000"), "OK", customer)));
    }

    @AfterEach
    public void tearDown() {
	userRepository.delete(user);
	roleRepository.deleteAll();
    }

    @Test
    public void findCustomerByCellPhone() {

	Customer customer = customerRepository.findCellphone("16992700000").get();

	assertNotNull(customer);

	assertEquals("16992700000", customer.getCellPhone());
	assertEquals("Tiago", customer.getName());
	assertEquals("tiago123@gmail.com", customer.getEmail());

    }

    @Test
    public void findCustomerByCellPhoneWhenReturnIsNull() {

	Optional<Customer> customer = customerRepository.findCellphone("16992700001");

	assertFalse(customer.isPresent());

    }

}