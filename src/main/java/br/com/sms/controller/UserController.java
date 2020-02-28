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

    @GetMapping("/statistic")
    public ResponseEntity<UserStatistics> getStatisticsByUserId(
	    @RequestParam(name = "user", required = true) String userId) {
	return ResponseEntity.ok(userService.getUserStatistics(userId));
    }

    @GetMapping("/user/message-prefix")
    public ResponseEntity<String> messagePrefix(@RequestParam(name = "user", required = true) String userId) {
	return ResponseEntity.ok(userService.messagePrefix(userId));
    }

    @GetMapping("/user/cron")
    public ResponseEntity<String> cronJob() {
	return ResponseEntity.ok("Checked - " + LocalDateTime.now());
    }

}
