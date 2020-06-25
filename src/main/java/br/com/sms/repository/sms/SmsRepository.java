package br.com.sms.repository.sms;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.sms.model.SmsId;
import org.springframework.data.jpa.domain.Specification;

import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;

public interface SmsRepository {

    SMS save(SMS sms);

    List<SMS> saveAll(List<SMS> sms);

//    Page<SMS> findAllSmsByUserId(UUID id);

    List<SMS> findSmsByFilter(SmsFilter smsFilter);

    List<SMS> findAll(Specification<SMS> spec);

    Optional<SMS> findSmsWithSmsId(SmsId smsId);

}
