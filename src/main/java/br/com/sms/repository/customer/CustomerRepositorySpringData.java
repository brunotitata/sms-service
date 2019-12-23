package br.com.sms.repository.customer;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sms.model.Customer;

public interface CustomerRepositorySpringData extends JpaRepository<Customer, UUID> {

    Page<Customer> findAllCustomerByUserId(UUID id, Pageable pageable);

}
