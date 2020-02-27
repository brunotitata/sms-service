package br.com.sms.dto;

public class SmsDTO {
    private String nameEmployee;
    private String number;
    private String messageBody;
    private String userId;

    public SmsDTO(String nameEmployee, String number, String messageBody, String userId) {
	this.nameEmployee = nameEmployee;
	this.number = number;
	this.messageBody = messageBody;
	this.userId = userId;
    }

    public SmsDTO() {
    }

    public String getNameEmployee() {
	return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
	this.nameEmployee = nameEmployee;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public String getMessageBody() {
	return messageBody;
    }

    public void setMessageBody(String messageBody) {
	this.messageBody = messageBody;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "SmsDTO [nameEmployee=" + nameEmployee + ", number=" + number + ", messageBody=" + messageBody
		+ ", userId=" + userId + "]";
    }

}
