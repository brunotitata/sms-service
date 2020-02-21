package br.com.sms.repository.employee;

import java.util.List;
import java.util.Set;

import br.com.sms.model.Employee;

public interface EmployeeRepository {

    void save(Employee employee);

    void deleteAll();

    Set<Employee> saveAll(Set<Employee> employees);

    List<Employee> findEmployeeByUserCpf(String userCpf);

}
