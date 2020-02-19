package br.com.sms.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sms.model.Customer;
import br.com.sms.model.User;
import br.com.sms.model.UserId;

public interface UserRepositorySpringData extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUserId(UserId clientId);

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmail(String email);

    @Query(value = "select customer.id, customer.cell_phone, customer.customer_id, customer.email, customer.name, customer.establishment_id from users \n"
	    + "inner join establishment on users.establishment_id = establishment.id \n"
	    + "inner join customer on establishment.id = :establishment", nativeQuery = true)
    List<Customer> findAllCustomer(@Param("establishment") UUID establishment);

}
