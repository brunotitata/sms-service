package br.com.sms.service;

public class SmsCommand {

    private String numberPhone;
    private String body;
    private String status;
    private String awsMessageId;
    private String userId;
    private String nameEmployee;
    private String messageError;

    public SmsCommand(String numberPhone, String body, String status, String awsMessageId, String userId,
	    String nameEmployee, String messageError) {
	setNumberPhone(numberPhone);
	setBody(body);
	setStatus(status);
	setAwsMessageId(awsMessageId);
	setUserId(userId);
	setNameEmployee(nameEmployee);
	setMessageError(messageError);
    }

    public SmsCommand() {
    }

    public String getNumberPhone() {
	return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
	this.numberPhone = numberPhone;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getAwsMessageId() {
	return awsMessageId;
    }

    public void setAwsMessageId(String awsMessageId) {
	this.awsMessageId = awsMessageId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getNameEmployee() {
	return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
	this.nameEmployee = nameEmployee;
    }

    public String getMessageError() {
	return messageError;
    }

    public void setMessageError(String messageError) {
	this.messageError = messageError;
    }

    @Override
    public String toString() {
	return "SmsCommand [numberPhone=" + numberPhone + ", body=" + body + ", status=" + status + ", awsMessageId="
		+ awsMessageId + ", userId=" + userId + ", nameEmployee=" + nameEmployee + ", messageError="
		+ messageError + "]";
    }

}
