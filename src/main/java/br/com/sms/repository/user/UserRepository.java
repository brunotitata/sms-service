package br.com.sms.repository.user;

import java.util.Optional;

import br.com.sms.model.User;

public interface UserRepository {

    User save(User client);

    void deleteAll();

    void delete(String userId);

    Optional<User> findUserByUserId(String userId);

    Boolean client(String userId);

    User findByCpf(String cpf);

    Boolean existClient(String cpf);

    Optional<User> findByEmail(String email);

}
