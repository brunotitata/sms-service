package br.com.sms.repository.sms;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;

public interface SmsRepository {

    SMS save(SMS sms);

    List<SMS> saveAll(List<SMS> sms);

//    Page<SMS> findAllSmsByUserId(UUID id);

    List<SMS> findSmsByFilter(SmsFilter smsFilter);

    List<SMS> findAll(Specification<SMS> spec);

}
