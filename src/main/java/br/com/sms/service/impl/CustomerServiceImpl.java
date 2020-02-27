package br.com.sms.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.EditCustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
import br.com.sms.login.exception.CustomerException;
import br.com.sms.login.util.Utils;
import br.com.sms.model.Customer;
import br.com.sms.model.CustomerId;
import br.com.sms.model.User;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.customer.CustomerSpecification;
import br.com.sms.repository.user.UserRepository;
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

	User user = userRepository.findUserByUserId(newCustomerDTO.getUserId()).orElseThrow(
		() -> new RuntimeException("Usuario não encontrado com ID: " + newCustomerDTO.getUserId()));

	user.getEstablishment().getCustomer().stream()
		.filter(customer -> customer.getCellPhone().equals(newCustomerDTO.getCellPhone())).findFirst()
		.ifPresent(customer -> {
		    throw new CustomerException(
			    "Cliente já cadastrado na plataforma: " + newCustomerDTO.getCellPhone());
		});

	return customerRepository.save(new Customer(new CustomerId(UUID.randomUUID()), newCustomerDTO.getName(),
		Utils.checkCharactersCellPhone(newCustomerDTO.getCellPhone()), newCustomerDTO.getEmail(),
		user.getEstablishment()));

    }

    @Override
    public void editCustomer(String userId, String cellphone, EditCustomerDTO customerDTO) {

	customerRepository.find(CustomerSpecification.findCustomerByCellphone(userId, cellphone))
		.stream()
		.filter(customer -> customer.getCellPhone().equals(cellphone))
		.findFirst()
		.ifPresent(customer -> {
		    customer.setCellPhone(customerDTO.getCellPhone());
		    customer.setEmail(customerDTO.getEmail());
		    customer.setName(customerDTO.getName());

		    customerRepository.save(customer);
		});

    }

    @Override
    public Page<CustomerDTO> findAllCustomerByUserId(String userId, Pageable pageable) {
	return customerRepository.findAllCustomerByUserId(CustomerSpecification.findCustomerByUserId(userId), pageable);
    }

    @Override
    public void removeCustomer(String userId, String cellphone) {

	customerRepository.find(CustomerSpecification.findCustomerByCellphone(userId, cellphone))
		.stream()
		.filter(customer -> customer.getCellPhone().equals(cellphone))
		.findFirst()
		.ifPresent(customer -> {
		    customerRepository.deleteCustomer(customer);
		});

    }

}
