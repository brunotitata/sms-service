package br.com.sms.repository.sms;

import br.com.sms.model.SMS;
import br.com.sms.model.SmsId;
import br.com.sms.repository.SmsFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SmsRepositoryJpa implements SmsRepository {

    private final SmsRepositorySpringData smsRepositorySpringData;

    public SmsRepositoryJpa(SmsRepositorySpringData customerRepositoryJpa) {
        this.smsRepositorySpringData = customerRepositoryJpa;
    }

    @Override
    public SMS save(SMS sms) {
        return smsRepositorySpringData.save(sms);
    }

    @Override
    public List<SMS> findSmsByFilter(SmsFilter smsFilter) {
        return smsRepositorySpringData.findAll(SmsSpecification.filter(smsFilter));
    }

    @Override
    public List<SMS> saveAll(List<SMS> sms) {
        return smsRepositorySpringData.saveAll(sms);
    }

    @Override
    public List<SMS> findAll(Specification<SMS> spec) {
        return smsRepositorySpringData.findAll(spec);
    }

    @Override
    public Optional<SMS> findSmsWithSmsId(SmsId smsId) {
        return smsRepositorySpringData.findBySmsId(smsId);
    }

}
