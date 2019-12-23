package br.com.sms.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerService {

    Customer newCustomer(CustomerDTO customerDTO);

    Page<Customer> findAllCustomerByUser(UUID userId);

}
