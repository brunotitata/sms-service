package br.com.sms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.dto.EmployeeDTO;
import br.com.sms.model.Employee;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private UserRepository userRepository;
    private EmployeeService employeeService;

    public EmployeeController(UserRepository userRepository, EmployeeService employeeService) {
	this.userRepository = userRepository;
	this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<String>> allEmployee(@RequestParam(name = "cpf", required = true) String userCpf) {
	return ResponseEntity.ok(userRepository.findByCpf(userCpf).getEstablishment().getEmployee().stream()
		.map(employee -> employee.getNome()).collect(Collectors.toList()));
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> save(@RequestBody EmployeeDTO employeeDTO) {

	employeeService.employeeDto(employeeDTO);

	return ResponseEntity.ok().build();
    }
}
