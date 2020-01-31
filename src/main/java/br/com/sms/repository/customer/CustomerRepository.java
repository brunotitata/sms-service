package br.com.sms.repository.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    Page<CustomerDTO> findAllCustomerByUserId(UUID id, Pageable pageable);

    Customer findById(UUID id);

    List<Customer> findCustomer(String name, String cellphone);

    void removeCustomer(String cellphone);

    Optional<Customer> findCellphone(String cellphone);

}
