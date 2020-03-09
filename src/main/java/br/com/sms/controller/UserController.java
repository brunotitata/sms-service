package br.com.sms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.dto.UserDTO;
import br.com.sms.model.User;
import br.com.sms.model.UserStatistics;
import br.com.sms.repository.user.UserRepositorySpringData;
import br.com.sms.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private UserRepositorySpringData userRepositorySpringData;

    public UserController(UserService userService, UserRepositorySpringData userRepositorySpringData) {
	this.userService = userService;
	this.userRepositorySpringData = userRepositorySpringData;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> user(@PathVariable(name = "userId", required = true) String userId) {
	return ResponseEntity.ok(userService.user(userId));
    }

    @PutMapping("/user/{userId}/edit")
    public ResponseEntity<Void> edit(@PathVariable(name = "userId", required = true) String userId,
	    @RequestBody UserDTO userDto) {

	userService.userEdit(userId, userDto);

	return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/cron")
    public ResponseEntity<String> cronJob() {
	return ResponseEntity.ok("Checked - " + LocalDateTime.now());
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> all() {
	return ResponseEntity.ok(userRepositorySpringData.findAll());
    }

}
