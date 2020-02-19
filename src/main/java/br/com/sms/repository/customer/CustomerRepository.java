package br.com.sms.repository.customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    void deleteAll();

    Set<Customer> saveAll(Set<Customer> customers);

//    Page<CustomerDTO> findAllCustomerByUserId(UUID id, Pageable pageable);

    Customer findById(UUID id);

    List<Customer> findCustomer(String name, String cellphone);

    void removeCustomer(String cellphone);

    Optional<Customer> findCellphone(String cellphone);

    List<Customer> find(Specification<Customer> user);

}
