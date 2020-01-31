package br.com.sms.repository.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.login.exception.CellphoneException;
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
    public void removeCustomer(String cellphone) {

	if (cellphone.isEmpty())
	    throw new ArgumentInvalidException("Para excluir um cliente, é preciso informar o numero do telefone.");

	Customer customer = customerRepositorySpringData.findByCellPhone(cellphone)
		.orElseThrow(() -> new CellphoneException("Customer não encontrado: " + cellphone));

	customerRepositorySpringData.delete(customer);
    }

    @Override
    public Optional<Customer> findCellphone(String cellphone) {
	return customerRepositorySpringData.findByCellPhone(cellphone);
    }

}
