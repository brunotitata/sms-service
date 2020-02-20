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

    Customer findById(UUID id);

    List<Customer> findCustomerByNameOrCellphone(String name, String cellPhone);

    void removeCustomer(String cellphone);

    void editCustomer(CustomerDTO cellphone);

    Page<CustomerDTO> findAllCustomerByUserCpf(String cpf, Pageable pageable);

}
