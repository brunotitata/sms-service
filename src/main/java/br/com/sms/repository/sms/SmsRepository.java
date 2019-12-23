package br.com.sms.repository.sms;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.sms.model.SMS;

public interface SmsRepository {

    SMS save(SMS sms);

    Page<SMS> findAllSmsByUserId(UUID id);

}
