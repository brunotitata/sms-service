package br.com.sms.dto;

public class EditCustomerDTO {

    private String name;
    private String cellPhone;
    private String email;

    public EditCustomerDTO(String name, String cellPhone, String email) {
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
    }

    public EditCustomerDTO() {
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
	return "EditCustomerDTO [name=" + name + ", cellPhone=" + cellPhone + ", email=" + email + "]";
    }

}
