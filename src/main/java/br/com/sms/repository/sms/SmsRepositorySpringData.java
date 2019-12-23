package br.com.sms.repository.sms;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sms.model.SMS;

public interface SmsRepositorySpringData extends JpaRepository<SMS, UUID> {

    Page<SMS> findByUserId(UUID id, Pageable pageable);

}
