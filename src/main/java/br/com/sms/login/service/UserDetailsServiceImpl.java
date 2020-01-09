package br.com.sms.login.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.sms.login.dto.UserDTO;
import br.com.sms.login.exception.EmailNotFoundException;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {

	User user = userRepository.findByEmail(email)
		.orElseThrow(() -> new EmailNotFoundException("User Not Found with -> email : " + email));

	return UserDTO.build(user);
    }

}
