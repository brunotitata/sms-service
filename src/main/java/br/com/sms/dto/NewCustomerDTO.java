package br.com.sms.dto;

public class NewCustomerDTO {

    private String name;
    private String cellPhone;
    private String email;
    private String userId;

    public NewCustomerDTO(String name, String cellPhone, String email, String userId) {
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
	this.userId = userId;
    }

    public NewCustomerDTO() {
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

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "NewCustomerDTO [name=" + name + ", cellPhone=" + cellPhone + ", email=" + email + ", userId=" + userId
		+ "]";
    }

}
