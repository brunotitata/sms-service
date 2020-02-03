package br.com.sms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SmsDTO {
    private String number;
    private String body;
    private UUID userId;
    private String statusServiceApi;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public SmsDTO(String number, String body, UUID userId) {
	this.number = number;
	this.body = body;
	this.userId = userId;
    }

    public SmsDTO(String number, String body, UUID userId, String statusServiceApi, LocalDateTime createdAt) {
	this.number = number;
	this.body = body;
	this.userId = userId;
	this.statusServiceApi = statusServiceApi;
	this.createdAt = createdAt;
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

    public String getStatusServiceApi() {
	return statusServiceApi;
    }

    public void setStatusServiceApi(String statusServiceApi) {
	this.statusServiceApi = statusServiceApi;
    }

    public LocalDateTime getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
    }

    @Override
    public String toString() {
	return "SmsDTO [number=" + number + ", body=" + body + ", userId=" + userId + ", statusServiceApi="
		+ statusServiceApi + ", createdAt=" + createdAt + "]";
    }

}
