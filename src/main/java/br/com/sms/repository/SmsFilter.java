package br.com.sms.repository;

public class SmsFilter {

    private String startDate;
    private String endDate;
    private String cellphone;
    private String message;
    private String nameCustomer;

    public SmsFilter(String startDate, String endDate, String cellphone, String message, String nameCustomer) {
	this.startDate = startDate;
	this.endDate = endDate;
	this.cellphone = cellphone;
	this.message = message;
	this.nameCustomer = nameCustomer;
    }

    public SmsFilter() {
    }

    public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
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
