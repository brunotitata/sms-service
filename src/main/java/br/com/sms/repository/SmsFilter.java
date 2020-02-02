package br.com.sms.repository;

import java.time.LocalDateTime;

public class SmsFilter {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String cellphone;
    private String message;
    private String nameCustomer;

    public SmsFilter(LocalDateTime startDate, LocalDateTime endDate, String cellphone, String message,
	    String nameCustomer) {
	this.startDate = startDate;
	this.endDate = endDate;
	this.cellphone = cellphone;
	this.message = message;
	this.nameCustomer = nameCustomer;
    }

    public SmsFilter() {
    }

    public LocalDateTime getStartDate() {
	return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
	this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
	return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
	this.endDate = endDate;
    }

    public String getCellphone() {
	return cellphone;
    }

    public void setCellphone(String cellphone) {
	this.cellphone = cellphone;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getNameCustomer() {
	return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
	this.nameCustomer = nameCustomer;
    }

    @Override
    public String toString() {
	return "SmsFilter [startDate=" + startDate + ", endDate=" + endDate + ", cellphone=" + cellphone + ", message="
		+ message + ", nameCustomer=" + nameCustomer + "]";
    }

}
