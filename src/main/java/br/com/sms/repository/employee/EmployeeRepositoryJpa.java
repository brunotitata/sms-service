package br.com.sms.repository.employee;

import java.util.HashSet;
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
    public void save(Employee employee) {
	employeeRepositorySpringData.save(employee);

    }

    @Override
    public Set<Employee> saveAll(Set<Employee> employees) {
	return new HashSet<>(employeeRepositorySpringData.saveAll(employees));
    }

    @Override
    public void deleteAll() {
	employeeRepositorySpringData.deleteAll();

    }

}
