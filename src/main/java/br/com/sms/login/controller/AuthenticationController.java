package br.com.sms.login.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sms.login.dto.LoginDTO;
import br.com.sms.login.dto.RegisterDTO;
import br.com.sms.login.exception.UserNotFoundException;
import br.com.sms.login.model.AccessToken;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.user.UserRepository;
import br.com.sms.login.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    private UserService userService;
    private UserRepository userRepository;

    public AuthenticationController(UserService userService, UserRepository userRepository) {
	this.userService = userService;
	this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> authenticateUser(@RequestBody LoginDTO loginData) {

	User user = userRepository.findByEmail(loginData.getEmail()).orElseThrow(
		() -> new UserNotFoundException("Usuario não encontrado com email: " + loginData.getEmail()));

	if (user.getActive().equals(false))
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	return ResponseEntity.ok().body(userService.authenticateUser(loginData));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerData) {

	User user = userService.registerUser(registerData);

	return ResponseEntity.created(
		ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri())
		.build();

    }

}
