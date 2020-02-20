package br.com.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import br.com.sms.dto.SmsDTO;
import br.com.sms.service.AwsService;

@Component
public class AwsServiceImpl implements AwsService {

    private AmazonSNS amazonSNS;

    public AwsServiceImpl(AmazonSNS amazonSNS) {
	this.amazonSNS = amazonSNS;
    }

    @Override
    public PublishResult sendSms(SmsDTO smsDTO) {

	Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
	smsAttributes.put("AWS.SNS.SMS.SMSType",
		new MessageAttributeValue().withStringValue("Promotional").withDataType("String"));

	PublishRequest request = new PublishRequest();
	request.withMessage(smsDTO.getMessageBody()).withPhoneNumber("+55".concat(smsDTO.getNumber()))
		.withMessageAttributes(smsAttributes);

	return amazonSNS.publish(request);
    }

}
