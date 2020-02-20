package br.com.sms.service;

import br.com.sms.dto.SmsDTO;

public interface SmsService {

    void send(SmsDTO smsDTO);

//    List<SmsDTO> smsReport(SmsFilter smsFilter);

}
