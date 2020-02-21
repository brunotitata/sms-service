package br.com.sms.repository.employee;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sms.model.Employee;

public interface EmployeeRepositorySpringData
	extends JpaRepository<Employee, UUID>, JpaSpecificationExecutor<Employee> {

}
