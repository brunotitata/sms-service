package br.com.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.login.exception.IllegalArgumentException;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.Customer;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
	this.customerRepository = customerRepository;
	this.userRepository = userRepository;
    }

    @Override
    public Customer newCustomer(CustomerDTO customerDTO) {

	User user = userRepository.findByIdUser(customerDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException(
		"Usuario não encontrado para associar a um Cliente: " + customerDTO.getUserId()));

	return customerRepository.save(new Customer(customerDTO.getName(), customerDTO.getLastName(),
		customerDTO.getAddress(), customerDTO.getCellPhone(), customerDTO.getTelephone(), user));

    }

    @Override
    public List<Customer> findAllCustomerByUser(Long userId) {
	return null;
    }

}
