package br.com.sms.smsservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.EditCustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
import br.com.sms.model.Customer;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.customer.CustomerSpecification;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.CustomerService;
import br.com.sms.smsservice.AbstractTests;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest extends AbstractTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
	userRepository.delete("46c2c662-d900-4f4f-9520-036c0a7ce1e9");

    }

    @Test
    public void removeCustomer() {

	customerService.removeCustomer("46c2c662-d900-4f4f-9520-036c0a7ce1e9", "12121212121");

	List<CustomerDTO> customers = customerService
		.findAllCustomerByUserId("46c2c662-d900-4f4f-9520-036c0a7ce1e9", PageRequest.of(0, 10)).getContent();

	assertEquals(1, customers.size());

	assertNotEquals("Borracheiro do João", customers.get(0).getName());
	assertNotEquals("12121212121", customers.get(0).getCellPhone());
	assertNotEquals("seizi@farmacia.com", customers.get(0).getEmail());

	assertEquals("Farmacia do Seizi", customers.get(0).getName());
	assertEquals("2222222222", customers.get(0).getCellPhone());
	assertEquals("borracheiro@loja.com", customers.get(0).getEmail());
    }

    @Test
    public void editCustomer() {

	customerService.editCustomer("46c2c662-d900-4f4f-9520-036c0a7ce1e9", "12121212121",
		new EditCustomerDTO("Farmacia do Seizi NOVO", "12121212121", "seizi-novo@farmacia.com", true));

	List<Customer> customer = customerRepository.find(
		CustomerSpecification.findCustomerByCellphone("46c2c662-d900-4f4f-9520-036c0a7ce1e9", "12121212121"));

	assertEquals(1, customer.size());

	assertEquals("Farmacia do Seizi NOVO", customer.get(0).getName());
	assertEquals("seizi-novo@farmacia.com", customer.get(0).getEmail());
	assertEquals("12121212121", customer.get(0).getCellPhone());

	assertNotEquals("Farmacia do Seizi", customer.get(0).getName());
	assertNotEquals("seizi@farmacia.com", customer.get(0).getEmail());
    }

    @Test
    public void newCustomer() {

	customerService.newCustomer(new NewCustomerDTO("Pedrita Lanches", "016999974444", "pedrita@gmail.com",
		"46c2c662-d900-4f4f-9520-036c0a7ce1e9", true));

	List<Customer> customers = customerRepository
		.find(CustomerSpecification.findCustomerByUserId("46c2c662-d900-4f4f-9520-036c0a7ce1e9"));

	assertEquals(3, customers.size());

	assertEquals("Farmacia do Seizi", customers.get(1).getName());
	assertEquals("Borracheiro do João", customers.get(0).getName());
	assertEquals("Pedrita Lanches", customers.get(2).getName());
    }

}
