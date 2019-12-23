package br.com.sms.repository.sms;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import br.com.sms.model.SMS;

@Repository
public class SmsRepositoryJpa implements SmsRepository {

    private SmsRepositorySpringData smsRepositorySpringData;

    public SmsRepositoryJpa(SmsRepositorySpringData customerRepositoryJpa) {
	this.smsRepositorySpringData = customerRepositoryJpa;
    }

    @Override
    public SMS save(SMS sms) {
	return smsRepositorySpringData.save(sms);
    }

    @Override
    public Page<SMS> findAllSmsByUserId(UUID id) {
	return smsRepositorySpringData.findByUserId(id, PageRequest.of(0, 20));
    }

}
