package br.com.sms.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sms.model.Employee;

public interface EmployeeRepositorySpringData extends JpaRepository<Employee, Long> {

}
