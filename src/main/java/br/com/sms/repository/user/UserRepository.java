package br.com.sms.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.sms.model.Customer;
import br.com.sms.model.User;

public interface UserRepository {

    User save(User client);

    void deleteAll();

    void delete(UUID uuid);

    Optional<User> findClientById(UUID uuid);

    Boolean client(UUID uuid);

    User findByCpf(String cpf);

    Boolean existClient(String cpf);

    User findByEmail(String email);

    List<Customer> findAllCustomers(UUID establishment);
}
