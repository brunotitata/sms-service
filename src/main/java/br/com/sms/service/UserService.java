package br.com.sms.service;

import java.util.UUID;

import br.com.sms.model.UserStatistics;

public interface UserService {

    UserStatistics getUserStatistics(UUID userId);

}
