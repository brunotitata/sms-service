package br.com.sms.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SmsCommand {

    private String numberPhone;
    private String body;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private UUID user;

    public SmsCommand(String numberPhone, String body, String status, LocalDateTime localDateTime, UUID user) {
	this.numberPhone = numberPhone;
	this.body = body;
	this.status = status;
	this.localDateTime = localDateTime;
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

    public LocalDateTime getLocalDateTime() {
	return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
	this.localDateTime = localDateTime;
    }

    public UUID getUser() {
	return user;
    }

    public void setUser(UUID user) {
	this.user = user;
    }

}
