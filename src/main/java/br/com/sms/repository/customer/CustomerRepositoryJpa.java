package br.com.sms.repository.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.sms.dto.CustomerDTO;
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
    public Page<CustomerDTO> findAllCustomerByUserId(UUID id, Pageable pageable) {
	return customerRepositorySpringData.findAllCustomerByUserId(id, pageable).map(Customer::convertToDto);
    }

    @Override
    public Customer findById(UUID id) {
	return customerRepositorySpringData.findById(id)
		.orElseThrow(() -> new CustomerNotFound("Cliente não encontrado: " + id));
    }

    @Override
    public List<Customer> findCustomer(String name, String cellphone) {
	return customerRepositorySpringData.findByNameOrCellPhoneContaining(Optional.ofNullable(name).orElse(""),
		Optional.ofNullable(cellphone).orElse(""));
    }

    @Override
    public void removeCustomer(UUID customerId) {

	Customer customer = customerRepositorySpringData.findById(customerId)
		.orElseThrow(() -> new CustomerNotFound("Customer não encontrado: " + customerId));

	customerRepositorySpringData.delete(customer);
    }

}
