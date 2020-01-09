package br.com.sms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
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
    public Customer newCustomer(NewCustomerDTO newCustomerDTO) {

	User user = userRepository.findById(newCustomerDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException(
		"Usuario não encontrado para associar a um Cliente: " + newCustomerDTO.getUserId()));

	return customerRepository.save(new Customer(newCustomerDTO.getName(), newCustomerDTO.getLastName(),
		newCustomerDTO.getAddress(), newCustomerDTO.getCellPhone(), newCustomerDTO.getTelephone(), user));

    }

    @Override
    public Page<CustomerDTO> findAllCustomerByUser(UUID userId, Pageable pageable) {
	return customerRepository.findAllCustomerByUserId(userId, pageable);
    }

    @Override
    public Customer findById(UUID id) {
	return customerRepository.findById(id);
    }

    @Override
    public List<Customer> customer(String name, String cellPhone) {
	return customerRepository.findCustomer(name, cellPhone);
    }

    @Override
    public void removeCustomer(UUID customerId) {
	customerRepository.removeCustomer(customerId);

    }

}
