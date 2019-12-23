package br.com.sms.login.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.sms.login.model.User;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private UserRepositorySpringData userRepositorySpringData;

    public UserRepositoryJpa(UserRepositorySpringData userRepositorySpringData) {
	this.userRepositorySpringData = userRepositorySpringData;
    }

    @Override
    public Optional<User> findByEmail(String email) {
	return userRepositorySpringData.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
	return userRepositorySpringData.existsByEmail(email);
    }

    @Override
    public User save(User user) {
	return userRepositorySpringData.save(user);
    }

    @Override
    public Optional<User> findById(UUID idUser) {
	return userRepositorySpringData.findById(idUser);
    }

}
