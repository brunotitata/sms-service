package br.com.sms.repository.employee;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.sms.model.Employee;

@Repository
public class EmployeeRepositoryJpa implements EmployeeRepository {

    private EmployeeRepositorySpringData employeeRepositorySpringData;

    public EmployeeRepositoryJpa(EmployeeRepositorySpringData employeeRepositorySpringData) {
	this.employeeRepositorySpringData = employeeRepositorySpringData;
    }

    @Override
    public Set<Employee> saveAll(Set<Employee> employees) {
	return new HashSet<>(employeeRepositorySpringData.saveAll(employees));
    }

    @Override
    public void deleteAll() {
	employeeRepositorySpringData.deleteAll();

    }

    @Override
    public List<Employee> findEmployeeByUserCpf(String userCpf) {
	return employeeRepositorySpringData.findAll(EmployeeSpecification.findAllEmployeeByUserCpf(userCpf));
    }

    @Override
    public Employee save(Employee employee) {
	return employeeRepositorySpringData.save(employee);
    }

}
