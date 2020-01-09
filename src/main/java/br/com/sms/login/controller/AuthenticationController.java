package br.com.sms.login.controller;

import javax.validation.Valid;

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
import br.com.sms.login.model.User;
import br.com.sms.login.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
	this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> authenticateUser(@RequestBody LoginDTO loginData) {
	return ResponseEntity.ok().body(userService.authenticateUser(loginData));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerData) {

	User user = userService.registerUser(registerData);

	return ResponseEntity.created(
		ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri())
		.build();

    }

}
