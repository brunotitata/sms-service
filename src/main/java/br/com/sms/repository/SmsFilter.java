package br.com.sms.repository;

public class SmsFilter {

    private String startDate;
    private String endDate;
    private String cpf;
    private String cellphone;
    private String message;
    private String nameCustomer;
    private String nameEmployee;
    private String status;

    public SmsFilter(String startDate, String endDate, String cpf, String cellphone, String message,
	    String nameCustomer, String nameEmployee, String status) {
	this.startDate = startDate;
	this.endDate = endDate;
	this.cpf = cpf;
	this.cellphone = cellphone;
	this.message = message;
	this.nameCustomer = nameCustomer;
	this.nameEmployee = nameEmployee;
	this.status = status;
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

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
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
	return "SmsFilter [startDate=" + startDate + ", endDate=" + endDate + ", cpf=" + cpf + ", cellphone="
		+ cellphone + ", message=" + message + ", nameCustomer=" + nameCustomer + ", nameEmployee="
		+ nameEmployee + ", status=" + status + "]";
    }

}
