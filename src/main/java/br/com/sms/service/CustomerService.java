package br.com.sms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerService {

    Customer newCustomer(NewCustomerDTO customerDTO);

    void removeCustomer(String cpf, String cellphone);

    void editCustomer(String cpf, String cellphone, CustomerDTO customerDTO);

    Page<CustomerDTO> findAllCustomerByUserCpf(String cpf, Pageable pageable);

}
