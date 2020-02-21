package br.com.sms.repository.user;

import java.util.Optional;
import java.util.UUID;

import br.com.sms.model.User;

public interface UserRepository {

    User save(User client);

    void deleteAll();

    void delete(UUID uuid);

    Optional<User> findClientById(UUID uuid);

    Boolean client(UUID uuid);

    User findByCpf(String cpf);

    Boolean existClient(String cpf);

    Optional<User> findByEmail(String email);

}
