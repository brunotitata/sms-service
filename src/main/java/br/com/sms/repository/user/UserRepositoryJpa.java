package br.com.sms.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.Customer;
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
    public Optional<User> findClientById(UUID uuid) {
	return userRepositorySpringData.findByUserId(new UserId(uuid));
    }

    @Override
    public Boolean client(UUID uuid) {
	return userRepositorySpringData.findByUserId(new UserId(uuid)).isPresent();
    }

    @Override
    public void delete(UUID uuid) {

	User client = findClientById(uuid)
		.orElseThrow(() -> new ArgumentInvalidException("Cliente não encontrado com ID: " + uuid));

	userRepositorySpringData.delete(client);

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
    public User findByEmail(String email) {
	return userRepositorySpringData.findByEmail(email)
		.orElseThrow(() -> new ArgumentInvalidException("Cliente não encontrado com email: " + email));
    }

    @Override
    public List<Customer> findAllCustomers(UUID establishment) {
	return userRepositorySpringData.findAllCustomer(establishment);
    }

}
