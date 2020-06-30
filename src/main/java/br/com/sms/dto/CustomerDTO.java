package br.com.sms.dto;

public class CustomerDTO {

    private String name;
    private String cellPhone;
    private String email;
    private Integer counterSms;
    private Boolean active;

    public CustomerDTO(String name, String cellPhone, String email, Integer counterSms, Boolean active) {
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
	this.counterSms = counterSms;
	this.active = active;
    }

    public CustomerDTO() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCellPhone() {
	return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
	this.cellPhone = cellPhone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getCounterSms() {
	return counterSms;
    }

    public void setCounterSms(Integer counterSms) {
	this.counterSms = counterSms;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    @Override
    public String toString() {
	return "CustomerDTO [name=" + name + ", cellPhone=" + cellPhone + ", email=" + email + ", counterSms="
		+ counterSms + ", active=" + active + "]";
    }

}
