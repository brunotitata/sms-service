package br.com.sms.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sms.model.User;
import br.com.sms.model.UserId;

public interface UserRepositorySpringData extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    Optional<User> findByUserId(UserId clientId);

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmail(String email);

}
