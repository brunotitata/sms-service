package br.com.sms.login.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sms.login.dto.LoginDTO;
import br.com.sms.login.dto.RegisterDTO;
import br.com.sms.login.model.AccessToken;
import br.com.sms.login.service.UserService;
import br.com.sms.model.Active;
import br.com.sms.model.User;
import br.com.sms.repository.user.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    private UserService userService;
    private UserRepository clientRepository;

    public AuthenticationController(UserService userService, UserRepository clientRepository) {
	this.userService = userService;
	this.clientRepository = clientRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> authenticateUser(@RequestBody LoginDTO loginData) {

	User user = clientRepository.findByEmail(loginData.getEmail());

	if (user.getActive().equals(Active.INATIVO))
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	return ResponseEntity.ok().body(userService.authenticateUser(loginData));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerData) {

	User user = userService.registerUser(registerData);

	return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(user.getUserId().getId()).toUri()).build();

    }

}
