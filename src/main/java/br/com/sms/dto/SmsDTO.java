package br.com.sms.dto;

import java.util.UUID;

public class SmsDTO {
    private String number;
    private String body;
    private UUID userId;

    public SmsDTO(String number, String body, UUID userId) {
	this.number = number;
	this.body = body;
	this.userId = userId;
    }

    @SuppressWarnings("unused")
    private SmsDTO() {
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public UUID getUserId() {
	return userId;
    }

    public void setUserId(UUID userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "SmsDTO [number=" + number + ", body=" + body + ", userId=" + userId + "]";
    }

}
