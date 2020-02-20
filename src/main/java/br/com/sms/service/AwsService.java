package br.com.sms.service;

import com.amazonaws.services.sns.model.PublishResult;

import br.com.sms.dto.SmsDTO;

public interface AwsService {

    PublishResult sendSms(SmsDTO smsDTO);

}
