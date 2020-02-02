package br.com.sms.repository.sms;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import br.com.sms.model.SMS;
import br.com.sms.repository.ReportSmsSpecification;
import br.com.sms.repository.SmsFilter;

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
    public List<SMS> findSmsByFilter(SmsFilter smsFilter) {
	return smsRepositorySpringData.findAll(ReportSmsSpecification.filter(smsFilter));
    }

    @Override
    public List<SMS> saveAll(List<SMS> sms) {
	return smsRepositorySpringData.saveAll(sms);
    }

    @Override
    public List<SMS> findAll(Specification<SMS> spec) {
	return smsRepositorySpringData.findAll(spec);
    }

}
