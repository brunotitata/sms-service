package br.com.sms.repository.sms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.Customer;
import br.com.sms.model.Establishment;
import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;

public class SmsSpecification {

    public static final String ERROR_DATE = "StartDate não pode ser maior que EndDate.";

    public static Specification<SMS> filter(final SmsFilter smsFilter) {
	return new Specification<SMS>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<SMS> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Join<SMS, Establishment> joinEstablishment = root.join("establishment", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotBlank(smsFilter.getMessage())) {
		    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("messagem")),
			    "%" + smsFilter.getMessage().toUpperCase() + "%"));
		}

		if (StringUtils.isNoneBlank(smsFilter.getCellphone())) {
		    predicates.add(criteriaBuilder.like(root.get("numero"), "%" + smsFilter.getCellphone() + "%"));
		}

		if (StringUtils.isNoneBlank(smsFilter.getStatus())) {
		    predicates.add(criteriaBuilder.equal(root.get("status"), smsFilter.getStatus()));
		}

		if (StringUtils.isNoneBlank(smsFilter.getNameEmployee())) {
		    predicates.add(criteriaBuilder.equal(root.get("nomeFuncionario"), smsFilter.getNameEmployee()));
		}

		if (StringUtils.isNoneBlank(smsFilter.getNameCustomer())) {
		    Join<SMS, Customer> join = root.join("customer");

		    predicates.add(criteriaBuilder.like(join.get("name"), "%" + smsFilter.getNameCustomer() + "%"));
		}

		if (smsFilter.getStartDate() != null && smsFilter.getEndDate() != null) {

		    LocalDateTime newStartDate = LocalDateTime.parse(smsFilter.getStartDate().concat("T00:00:00.000"));
		    LocalDateTime newEndDate = LocalDateTime.parse(smsFilter.getEndDate().concat("T23:59:59.000"));

		    if (newStartDate.isAfter(newEndDate))
			throw new RuntimeException(ERROR_DATE);

		    predicates.add(criteriaBuilder.between(root.get("localDateTime"), newStartDate, newEndDate));
		}

		if (predicates.isEmpty()) {
		    return criteriaBuilder.conjunction();
		}

		predicates.add(criteriaBuilder.equal(joinEstablishment.get("user").get("cpf"), smsFilter.getCpf()));

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    }

	};

    }

}
