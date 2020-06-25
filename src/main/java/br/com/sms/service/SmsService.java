package br.com.sms.service;

import java.util.List;

import br.com.sms.dto.SmsDTO;
import br.com.sms.dto.SmsIdDTO;
import br.com.sms.dto.SmsSpecificationDTO;
import br.com.sms.repository.SmsFilter;

public interface SmsService {

    void send(SmsDTO smsDTO);

    List<SmsSpecificationDTO> smsReport(SmsFilter smsFilter);

    void sendAll(SmsDTO smsDTO);

    void reSend(SmsIdDTO smsId);

}
