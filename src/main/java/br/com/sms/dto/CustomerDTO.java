package br.com.sms.dto;

public class CustomerDTO {

    private String name;
    private String cellPhone;
    private String email;

    public CustomerDTO(String name, String cellPhone, String email) {
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
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

    @Override
    public String toString() {
	return "CustomerDTO [name=" + name + ", cellPhone=" + cellPhone + ", email=" + email + "]";
    }

}
