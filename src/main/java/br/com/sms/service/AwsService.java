package br.com.sms.service;

import com.amazonaws.services.sns.model.PublishResult;

public interface AwsService {

    PublishResult sendSms(String number, String messageBody);

}
