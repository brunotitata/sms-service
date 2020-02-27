package br.com.sms.repository.customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import br.com.sms.dto.CustomerDTO;
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
    public Set<Customer> saveAll(Set<Customer> customers) {
	return new HashSet<>(customerRepositorySpringData.saveAll(customers));
    }

    @Override
    public void deleteAll() {
	customerRepositorySpringData.deleteAll();

    }

    @Override
    public List<Customer> find(Specification<Customer> user) {
	return customerRepositorySpringData.findAll(user);
    }

    @Override
    public Page<CustomerDTO> findAllCustomerByUserId(Specification<Customer> customer, Pageable pageable) {
	return customerRepositorySpringData.findAll(customer, pageable).map(Customer::convertToDto);
    }

    @Override
    public List<Customer> findCustomerByUserCpfAndCellphone(String cpf, String cellphone) {
	return customerRepositorySpringData.findAll(CustomerSpecification.findCustomerByCellphone(cpf, cellphone));
    }

    @Override
    public void deleteCustomer(Customer customer) {
	customerRepositorySpringData.delete(customer);

    }

}
