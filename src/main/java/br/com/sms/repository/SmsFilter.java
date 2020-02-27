package br.com.sms.repository;

public class SmsFilter {

    private String startDate;
    private String endDate;
    private String userId;
    private String cellphone;
    private String message;
    private String nameCustomer;
    private String nameEmployee;
    private String status;

    public SmsFilter(String startDate, String endDate, String userId, String cellphone, String message,
	    String nameCustomer, String nameEmployee, String status) {
	this.startDate = startDate;
	this.endDate = endDate;
	this.userId = userId;
	this.cellphone = cellphone;
	this.message = message;
	this.nameCustomer = nameCustomer;
	this.nameEmployee = nameEmployee;
	this.status = status;
    }

    public SmsFilter(String startDate, String endDate, String userId) {
	this.startDate = startDate;
	this.endDate = endDate;
	this.userId = userId;
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

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
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

    public String getNameEmployee() {
	return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
	this.nameEmployee = nameEmployee;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "SmsFilter [startDate=" + startDate + ", endDate=" + endDate + ", userId=" + userId + ", cellphone="
		+ cellphone + ", message=" + message + ", nameCustomer=" + nameCustomer + ", nameEmployee="
		+ nameEmployee + ", status=" + status + "]";
    }

}
