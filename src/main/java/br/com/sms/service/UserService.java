package br.com.sms.service;

import br.com.sms.model.UserStatistics;

public interface UserService {

    UserStatistics getUserStatistics(String cpf);

    String messagePrefix(String userId);

}
