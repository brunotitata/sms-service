package br.com.sms.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.User;
import br.com.sms.model.UserId;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private UserRepositorySpringData userRepositorySpringData;

    public UserRepositoryJpa(UserRepositorySpringData clientRepositorySpringData) {
	this.userRepositorySpringData = clientRepositorySpringData;
    }

    @Override
    public User save(User client) {
	return userRepositorySpringData.save(client);

    }

    @Override
    public void deleteAll() {
	userRepositorySpringData.deleteAll();

    }

    @Override
    public Optional<User> findUserByUserId(String uuid) {
	return userRepositorySpringData.findByUserId(new UserId(UUID.fromString(uuid)));
    }

    @Override
    public Boolean client(String userId) {
	return userRepositorySpringData.findByUserId(new UserId(UUID.fromString(userId))).isPresent();
    }

    @Override
    public User findByCpf(String cpf) {
	return userRepositorySpringData.findByCpf(cpf)
		.orElseThrow(() -> new ArgumentInvalidException("Cliente não foi encontrado com CPF: " + cpf));
    }

    @Override
    public Boolean existClient(String cpf) {
	return userRepositorySpringData.findByCpf(cpf).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
	return userRepositorySpringData.findByEmail(email);
    }

    @Override
    public void delete(String userId) {

	User user = findUserByUserId(userId)
		.orElseThrow(() -> new ArgumentInvalidException("Cliente não encontrado com ID: " + userId));

	userRepositorySpringData.delete(user);

    }

}
