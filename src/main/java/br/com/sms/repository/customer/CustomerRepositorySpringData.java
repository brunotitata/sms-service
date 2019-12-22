package br.com.sms.repository.customer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sms.model.Customer;

public interface CustomerRepositorySpringData extends JpaRepository<Customer, UUID> {

}
