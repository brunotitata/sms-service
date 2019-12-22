package br.com.sms.repository.sms;

import org.springframework.stereotype.Repository;

import br.com.sms.model.SMS;

@Repository
public class SmsRepositoryJpa implements SmsRepository {

    private SmsRepositorySpringData SmsRepositorySpringData;

    public SmsRepositoryJpa(SmsRepositorySpringData customerRepositoryJpa) {
	this.SmsRepositorySpringData = customerRepositoryJpa;
    }

    @Override
    public SMS save(SMS sms) {
	return SmsRepositorySpringData.save(sms);
    }

}
