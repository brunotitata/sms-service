package br.com.sms.service;

import br.com.sms.dto.CreditDTO;
import br.com.sms.dto.UserDTO;
import br.com.sms.model.UserStatistics;

public interface UserService {

    UserStatistics getUserStatistics(String cpf);

    String messagePrefix(String userId);

    UserDTO user(String userId);

    void userEdit(String userId, UserDTO userDTO);

    void addCredit(CreditDTO creditoDTO);

}
