package br.com.sms.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sms.dto.EmployeeDTO;
import br.com.sms.login.exception.EmployeeExistingException;
import br.com.sms.model.Employee;
import br.com.sms.model.EmployeeId;
import br.com.sms.model.User;
import br.com.sms.repository.employee.EmployeeRepository;
import br.com.sms.repository.user.UserRepository;
import br.com.sms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
	this.employeeRepository = employeeRepository;
	this.userRepository = userRepository;
    }

    @Override
    public EmployeeDTO employeeDto(EmployeeDTO employeeDTO) {

	User user = userRepository.findUserByUserId(employeeDTO.getUserId())
		.orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + employeeDTO.getUserId()));

	user.getEstablishment().getEmployee().stream()
		.filter(employee -> employee.getEmail().equals(employeeDTO.getEmail())).findFirst()
		.ifPresent(employee -> {
		    throw new EmployeeExistingException(
			    "Funcionário(a) já cadastrado na plataforma com email: " + employeeDTO.getEmail());

		});

	Employee employee = employeeRepository.save(new Employee(new EmployeeId(UUID.randomUUID()),
		employeeDTO.getNome(), employeeDTO.getEmail(), null, employeeDTO.getActive(), user.getEstablishment()));

	return new EmployeeDTO(employee.getNome(), employee.getEmail(), employee.getActive(), employeeDTO.getUserId());
    }

}
