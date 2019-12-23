package br.com.sms.login.repository.user;

import java.util.Optional;
import java.util.UUID;

import br.com.sms.login.model.User;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findById(UUID idUser);

}
