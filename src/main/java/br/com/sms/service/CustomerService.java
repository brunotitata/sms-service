package br.com.sms.service;

import java.util.List;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerService {

    Customer newCustomer(CustomerDTO customerDTO);

    List<Customer> findAllCustomerByUser(Long userId);

}
