package br.com.sms.service;

import java.util.UUID;

public class SmsCommand {

    private String numberPhone;
    private String body;
    private String status;
    private UUID user;

    public SmsCommand(String numberPhone, String body, String status, UUID user) {
	this.numberPhone = numberPhone;
	this.body = body;
	this.status = status;
	this.user = user;
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

    public UUID getUser() {
	return user;
    }

    public void setUser(UUID user) {
	this.user = user;
    }

}
