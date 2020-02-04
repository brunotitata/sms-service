package br.com.sms.service;

import java.util.List;

import br.com.sms.dto.SmsDTO;
import br.com.sms.repository.SmsFilter;

public interface SmsService {

    void send(SmsDTO smsDTO);

    List<SmsDTO> smsReport(SmsFilter smsFilter);

}
