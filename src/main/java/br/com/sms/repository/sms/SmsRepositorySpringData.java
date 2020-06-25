package br.com.sms.repository.sms;

import br.com.sms.model.SMS;
import br.com.sms.model.SmsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface SmsRepositorySpringData extends JpaRepository<SMS, Long>, JpaSpecificationExecutor<SMS> {

    Optional<SMS> findBySmsId(SmsId smsId);

}
