package br.com.sms.repository.customer;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

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
    public Page<Customer> findAllCustomerByUserId(UUID id) {
	return customerRepositorySpringData.findAllCustomerByUserId(id, PageRequest.of(0, 20));
    }

}
