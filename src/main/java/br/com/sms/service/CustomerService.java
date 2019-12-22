package br.com.sms.service;

import org.springframework.stereotype.Service;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.login.exception.IllegalArgumentException;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.Customer;
import br.com.sms.repository.customer.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
	this.customerRepository = customerRepository;
	this.userRepository = userRepository;
    }

    public Customer newCustomer(CustomerDTO customerDTO) {

	User user = userRepository.findByIdUser(customerDTO.getUserId())
		.orElseThrow(() -> new IllegalArgumentException(""));

	return customerRepository.save(new Customer(customerDTO.getName(), customerDTO.getLastName(),
		customerDTO.getAddress(), customerDTO.getCellPhone(), customerDTO.getTelephone(), user));
    }

}
