package br.com.sms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.Customer;
import br.com.sms.model.SMS;

public class ReportSmsSpecification {

    public static final String ERROR_DATE = "StartDate não pode ser maior que EndDate.";

    public static Specification<SMS> filter(final SmsFilter smsFilter) {
	return new Specification<SMS>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<SMS> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotBlank(smsFilter.getMessage())) {
		    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("body")),
			    "%" + smsFilter.getMessage().toUpperCase() + "%"));
		}

		if (StringUtils.isNoneBlank(smsFilter.getCellphone())) {
		    predicates.add(criteriaBuilder.like(root.get("numberPhone"), "%" + smsFilter.getCellphone() + "%"));
		}

		if (StringUtils.isNoneBlank(smsFilter.getNameCustomer())) {
		    Join<SMS, Customer> join = root.join("customer");

		    predicates.add(criteriaBuilder.like(join.get("name"), "%" + smsFilter.getNameCustomer() + "%"));
		}

		if (smsFilter.getStartDate() != null && smsFilter.getEndDate() != null) {

		    if (smsFilter.getStartDate().isAfter(smsFilter.getEndDate()))
			throw new RuntimeException(ERROR_DATE);

		    predicates.add(criteriaBuilder.between(root.get("createdAt"), smsFilter.getStartDate(),
			    smsFilter.getEndDate()));
		}

		if (predicates.isEmpty()) {
		    return criteriaBuilder.conjunction();
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    }

	};

    }
}
