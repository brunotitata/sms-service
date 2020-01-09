package br.com.sms.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerService {

    Customer newCustomer(NewCustomerDTO customerDTO);

    Page<CustomerDTO> findAllCustomerByUser(UUID userId, Pageable pageable);

    Customer findById(UUID id);

    List<Customer> customer(String name, String cellPhone);

    void removeCustomer(UUID customerId);

}
