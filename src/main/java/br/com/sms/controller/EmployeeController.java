package br.com.sms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.repository.user.UserRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private UserRepository userRepository;

    public EmployeeController(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<String>> allEmployee(@RequestParam(name = "cpf", required = true) String userCpf) {
	return ResponseEntity.ok(userRepository.findByCpf(userCpf).getEstablishment().getEmployee().stream()
		.map(employee -> employee.getNome()).collect(Collectors.toList()));
    }

}
