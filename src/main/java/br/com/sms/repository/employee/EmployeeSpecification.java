package br.com.sms.repository.employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.Employee;
import br.com.sms.model.Establishment;

public class EmployeeSpecification {

    public static Specification<Employee> findAllEmployeeByUserCpf(String cpf) {
	return new Specification<Employee>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Join<Employee, Establishment> joinEstablishment = root.join("establishment", JoinType.INNER);

		return criteriaBuilder.equal(joinEstablishment.get("user").get("cpf"), cpf);

	    }

	};

    }

}
