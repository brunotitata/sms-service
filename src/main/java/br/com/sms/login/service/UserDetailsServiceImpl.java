package br.com.sms.login.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.sms.login.dto.UserDTO;
import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.User;
import br.com.sms.repository.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository clientRepository;

    public UserDetailsServiceImpl(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {

        User user = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ArgumentInvalidException("Cliente não encontrado com email: " + email));

        return UserDTO.build(user);
    }

}
