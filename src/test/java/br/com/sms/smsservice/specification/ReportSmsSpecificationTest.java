package br.com.sms.smsservice.specification;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
import br.com.sms.repository.ReportSmsSpecification;
import br.com.sms.repository.SmsFilter;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.sms.SmsRepository;

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
		.findAll(ReportSmsSpecification.filter(new SmsFilter(LocalDateTime.parse("2020-02-01T00:00:00.000"),
			LocalDateTime.parse("2020-02-01T23:59:21.000"), null, null, null)));

	assertEquals(1, sms.size());
	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());

    }

    @Test
    public void smsFilterWithStartDateAndEndDateNotExist() {

	List<SMS> sms = smsRepository
		.findAll(ReportSmsSpecification.filter(new SmsFilter(LocalDateTime.parse("2020-08-01T00:00:00.000"),
			LocalDateTime.parse("2020-10-01T23:59:21.000"), null, null, null)));

	assertEquals(0, sms.size());

    }

    @Test
    public void smsFilterWithStartDateIsAfterEndDateShouldReturnException() {

	assertThatThrownBy(() -> smsRepository
		.findAll(ReportSmsSpecification.filter(new SmsFilter(LocalDateTime.parse("2020-09-01T00:00:00.000"),
			LocalDateTime.parse("2020-07-01T23:59:21.000"), null, null, null))))
				.isInstanceOf(RuntimeException.class).hasMessage(ReportSmsSpecification.ERROR_DATE);
    }

    @Test
    public void smsFilterWithCellPhone() {

	List<SMS> sms = smsRepository
		.findAll(ReportSmsSpecification.filter(new SmsFilter(null, null, "1111", null, null)));

	assertEquals(1, sms.size());
	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());
    }

    @Test
    public void smsFilterWithNameCustomer() {

	List<SMS> sms = smsRepository
		.findAll(ReportSmsSpecification.filter(new SmsFilter(null, null, null, null, "Tiago")));

	assertEquals(3, sms.size());

	assertEquals("messagem 1", sms.get(0).getBody());
	assertEquals("11111111111", sms.get(0).getNumberPhone());

	assertEquals("messagem 2", sms.get(1).getBody());
	assertEquals("22222222222", sms.get(1).getNumberPhone());

	assertEquals("messagem 3", sms.get(2).getBody());
	assertEquals("33333333333", sms.get(2).getNumberPhone());
    }

}
