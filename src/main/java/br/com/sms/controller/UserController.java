package br.com.sms.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.model.UserStatistics;
import br.com.sms.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
	this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserStatistics> getStatisticsByUserId(
	    @RequestParam(name = "cpf", required = true) String userCpf) {
	return ResponseEntity.ok(userService.getUserStatistics(userCpf));
    }

    @GetMapping("/user/cron")
    public ResponseEntity<String> cronJob() {
	return ResponseEntity.ok("Checked - " + LocalDateTime.now());
    }

}
