//package br.com.sms.service.impl;
//
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import br.com.sms.dto.CustomerDTO;
//import br.com.sms.dto.NewCustomerDTO;
//import br.com.sms.login.exception.ArgumentInvalidException;
//import br.com.sms.login.exception.CustomerException;
//import br.com.sms.login.exception.IllegalArgumentException;
//import br.com.sms.login.util.Utils;
//import br.com.sms.model.Customer;
//import br.com.sms.model.User;
//import br.com.sms.repository.customer.CustomerRepository;
//import br.com.sms.repository.user.UserRepository;
//import br.com.sms.service.CustomerService;
//
//@Service
//public class CustomerServiceImpl implements CustomerService {
//
//    private CustomerRepository customerRepository;
//    private UserRepository userRepository;
//
//    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
//	this.customerRepository = customerRepository;
//	this.userRepository = userRepository;
//    }
//
//    @Override
//    public Customer newCustomer(NewCustomerDTO newCustomerDTO) {
//
//	customerRepository.findCellphone(newCustomerDTO.getCellPhone()).ifPresent(customer -> {
//	    throw new CustomerException("Cliente já cadastrado na base com telefone: " + newCustomerDTO.getCellPhone());
//	});
//
//	return customerRepository.save(
//		new Customer(newCustomerDTO.getName(), Utils.checkCharactersCellPhone(newCustomerDTO.getCellPhone()),
//			newCustomerDTO.getEmail(), checkForUser(newCustomerDTO)));
//
//    }
//
//    private User checkForUser(NewCustomerDTO newCustomerDTO) {
//	return userRepository.findById(newCustomerDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException(
//		"Usuario não encontrado para associar a um Cliente: " + newCustomerDTO.getUserId()));
//    }
//
//    @Override
//    public Page<CustomerDTO> findAllCustomerByUser(UUID userId, Pageable pageable) {
//	return customerRepository.findAllCustomerByUserId(userId, pageable);
//    }
//
//    @Override
//    public Customer findById(UUID id) {
//	return customerRepository.findById(id);
//    }
//
//    @Override
//    public List<Customer> findCustomerByNameOrCellphone(String name, String cellPhone) {
//	return customerRepository.findCustomer(name, cellPhone);
//    }
//
//    @Override
//    public void removeCustomer(String cellphone) {
//	customerRepository.removeCustomer(cellphone);
//
//    }
//
//    @Override
//    public void editCustomer(CustomerDTO customerDTO) {
//
//	Customer customer = customerRepository.findCellphone(customerDTO.getCellPhone())
//		.orElseThrow(() -> new ArgumentInvalidException(
//			"Cliente não encontrado com celular: " + customerDTO.getCellPhone()));
//
//	customer.setCellPhone(customerDTO.getCellPhone());
//	customer.setEmail(customerDTO.getEmail());
//	customer.setName(customerDTO.getName());
//
//	customerRepository.save(customer);
//
//    }
//
//}
