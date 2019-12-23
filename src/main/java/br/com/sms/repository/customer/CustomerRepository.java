package br.com.sms.repository.customer;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.sms.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    Page<Customer> findAllCustomerByUserId(UUID id);

}
