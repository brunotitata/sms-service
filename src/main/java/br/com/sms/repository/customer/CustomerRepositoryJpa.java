package br.com.sms.repository.customer;

import org.springframework.stereotype.Repository;

import br.com.sms.model.Customer;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    private CustomerRepositorySpringData CustomerRepositorySpringData;

    public CustomerRepositoryJpa(CustomerRepositorySpringData customerRepositoryJpa) {
	this.CustomerRepositorySpringData = customerRepositoryJpa;
    }

    @Override
    public Customer save(Customer customer) {
	return CustomerRepositorySpringData.save(customer);
    }

}
