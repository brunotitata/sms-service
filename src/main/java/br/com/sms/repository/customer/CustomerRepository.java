package br.com.sms.repository.customer;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    void deleteAll();

    Set<Customer> saveAll(Set<Customer> customers);

    Page<CustomerDTO> findAllCustomerByUserId(Specification<Customer> customer, Pageable pageable);

    void deleteCustomer(Customer customer);

    List<Customer> find(Specification<Customer> user);

    List<Customer> findCustomerByUserCpfAndCellphone(String cpf, String cellphone);

}
