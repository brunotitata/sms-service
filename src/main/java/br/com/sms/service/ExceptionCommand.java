package br.com.sms.service;

import org.springframework.http.HttpStatus;

public class ExceptionCommand {

    private String body;
    private String message;
    private HttpStatus httpStatus;

    public ExceptionCommand(String body, String message, HttpStatus httpStatus) {
	this.body = body;
	this.message = message;
	this.httpStatus = httpStatus;
    }

    @SuppressWarnings("unused")
    private ExceptionCommand() {
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public HttpStatus getHttpStatus() {
	return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
	this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
	return "ExceptionCommand [body=" + body + ", message=" + message + ", httpStatus=" + httpStatus + "]";
    }

}
