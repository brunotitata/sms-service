package br.com.sms.repository.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import br.com.sms.model.Customer;

public interface CustomerRepositorySpringData
	extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

//    @Transactional(readOnly = true)
//    Page<Customer> findAllCustomerByUserId(UUID id, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Customer> findByEstablishment_Id(UUID uuid);

    @Transactional(readOnly = true)
    List<Customer> findByNameOrCellPhoneContaining(String name, String cellPhone);

    @Transactional(readOnly = true)
    Optional<Customer> findByCellPhone(String cellphone);

}
