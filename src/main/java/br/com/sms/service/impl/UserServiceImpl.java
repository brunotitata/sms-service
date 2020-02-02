package br.com.sms.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.model.UserStatistics;
import br.com.sms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Override
    public UserStatistics getUserStatistics(UUID userId) {
	return userRepository.findById(userId).map(User::getStatistic)
		.orElseThrow(() -> new ArgumentInvalidException("User não encontrado com id: " + userId));
    }

}
