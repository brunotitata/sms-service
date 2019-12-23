package br.com.sms.repository.customer;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.sms.login.exception.CustomerNotFound;
import br.com.sms.model.Customer;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    private CustomerRepositorySpringData customerRepositorySpringData;

    public CustomerRepositoryJpa(CustomerRepositorySpringData customerRepositoryJpa) {
	this.customerRepositorySpringData = customerRepositoryJpa;
    }

    @Override
    public Customer save(Customer customer) {
	return customerRepositorySpringData.save(customer);
    }

    @Override
    public Page<Customer> findAllCustomerByUserId(UUID id, Pageable pageable) {
	return customerRepositorySpringData.findAllCustomerByUserId(id, pageable);
    }

    @Override
    public Customer findById(UUID id) {
	return customerRepositorySpringData.findById(id)
		.orElseThrow(() -> new CustomerNotFound("Cliente não encontrado: " + id));
    }

}
