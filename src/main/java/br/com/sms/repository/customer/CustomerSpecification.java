package br.com.sms.repository.customer;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.Customer;
import br.com.sms.model.Establishment;

public class CustomerSpecification {

    public static Specification<Customer> findCustomer(String establishmentId) {
	return new Specification<Customer>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Join<Customer, Establishment> joinEstablishment = root.join("establishment", JoinType.INNER);

		return criteriaBuilder.equal(joinEstablishment.get("establishmentId").get("id"),
			UUID.fromString(establishmentId));

	    }

	};

    }

    public static Specification<Customer> findCustomerByUserId(String userId) {
	return new Specification<Customer>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Join<Customer, Establishment> joinEstablishment = root.join("establishment", JoinType.INNER);

		return criteriaBuilder.equal(joinEstablishment.get("user").get("userId").get("id"),
			UUID.fromString(userId));

	    }

	};

    }

    public static Specification<Customer> findCustomerByCellphone(String userId, String cellphone) {
	return new Specification<Customer>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Join<Customer, Establishment> joinEstablishment = root.join("establishment", JoinType.INNER);

		Predicate equal = criteriaBuilder.equal(root.get("cellPhone"), cellphone);
		Predicate equal2 = criteriaBuilder.equal(joinEstablishment.get("user").get("userId").get("id"),
			UUID.fromString(userId));

		return criteriaBuilder.and(equal, equal2);

	    }

	};

    }

}
