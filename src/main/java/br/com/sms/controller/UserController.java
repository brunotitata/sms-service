package br.com.sms.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//package br.com.sms.controller;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.sms.model.UserStatistics;
//import br.com.sms.service.UserService;
//
@RestController
@RequestMapping("/api")
public class UserController {
//
//    private UserService userService;
//
//    public UserController(UserService userService) {
//	this.userService = userService;
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<UserStatistics> getStatisticsByUserId(@PathVariable String userId) {
//	return ResponseEntity.ok(userService.getUserStatistics(UUID.fromString(userId)));
//    }
//
    @GetMapping("/user/cron")
    public ResponseEntity<String> cronJob() {
	return ResponseEntity.ok("Checked - " + LocalDateTime.now());
    }

}
