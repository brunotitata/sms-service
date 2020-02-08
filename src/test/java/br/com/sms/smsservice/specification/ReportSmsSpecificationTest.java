package br.com.sms.smsservice.specification;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.role.RoleRepository;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.Customer;
import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.sms.SmsRepository;
import br.com.sms.repository.sms.SmsSpecification;

@SpringBootTest
public class ReportSmsSpecificationTest {

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;
    private Customer customer;

    @BeforeEach
    public void setUp() {

	user = new User("Bruno Costa", "Estabelecimento Teste", "bruno123@bruno.com", passwordEncoder.encode("123456"),
		1000, true);
	Role role = roleRepository.findByName(RoleName.ROLE_USER).get();
	user.setRoles(Collections.singleton(role));
	userRepository.save(user);

	customer = customerRepository.save(new Customer("Tiago", "16992700000", "tiago123@gmail.com", user));

	smsRepository.saveAll(Arrays.asList(
		new SMS("11111111111", "messagem 1", LocalDateTime.parse("2020-02-01T16:25:21.000"), "OK", customer),
		new SMS("22222222222", "messagem 2", LocalDateTime.parse("2020-01-28T19:43:21.000"), "OK", customer),
		new SMS("33333333333", "messagem 3", LocalDateTime.parse("2020-01-23T23:01:21.000"), "OK", customer)));
    }

    @AfterEach
    public void tearDown() {
	userRepository.delete(user);
    }

//    @Test
//    public void smsFilterWithAllParamsNull() {
//
//	List<SMS> sms = smsRepository
//		.findAll(ReportSmsSpecification.filter(new SmsFilter(null, null, null, null, null)));
//
//	System.out.println();
//	assertEquals(0, sms.size());
//    }

    @Test
    public void smsFilterWithStartDateAndEndDate() {

	List<SMS> sms = smsRepository
		.findAll(SmsSpecification.filter(new SmsFilter("2020-02-01", "2020-02-01", null, null, null)));

	assertEquals(1, sms.size());
	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());

    }

    @Test
    public void smsFilterWithStartDateAndEndDateNotExist() {

	List<SMS> sms = smsRepository
		.findAll(SmsSpecification.filter(new SmsFilter("2020-08-01", "2020-10-01", null, null, null)));

	assertEquals(0, sms.size());

    }

    @Test
    public void smsFilterWithStartDateIsAfterEndDateShouldReturnException() {

	assertThatThrownBy(() -> smsRepository
		.findAll(SmsSpecification.filter(new SmsFilter("2020-09-01", "2020-07-01", null, null, null))))
			.isInstanceOf(RuntimeException.class).hasMessage(SmsSpecification.ERROR_DATE);
    }

    @Test
    public void smsFilterWithCellPhone() {

	List<SMS> sms = smsRepository.findAll(SmsSpecification.filter(new SmsFilter(null, null, "1111", null, null)));

	assertEquals(1, sms.size());
	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());
    }

    @Test
    public void smsFilterWithNameCustomer() {

	List<SMS> sms = smsRepository.findAll(SmsSpecification.filter(new SmsFilter(null, null, null, null, "Tiago")));

	assertEquals(3, sms.size());

	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());

	assertEquals("messagem 2", sms.get(1).getBody());
	assertEquals("22222222222", sms.get(1).getNumberPhone());

	assertEquals("messagem 3", sms.get(2).getBody());
	assertEquals("33333333333", sms.get(2).getNumberPhone());
    }

}
