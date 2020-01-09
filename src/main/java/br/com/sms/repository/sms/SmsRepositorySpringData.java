package br.com.sms.repository.sms;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sms.model.SMS;

public interface SmsRepositorySpringData extends JpaRepository<SMS, UUID> {

    @Transactional(readOnly = true)
    Page<SMS> findByUserId(UUID id, Pageable pageable);

}
