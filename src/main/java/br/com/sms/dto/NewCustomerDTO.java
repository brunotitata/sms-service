package br.com.sms.dto;

import java.util.UUID;

public class NewCustomerDTO {

    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String email;
    private UUID userId;

    public NewCustomerDTO(String name, String lastName, String address, String cellPhone, String email) {
	this.name = name;
	this.lastName = lastName;
	this.address = address;
	this.cellPhone = cellPhone;
	this.email = email;
    }

    public NewCustomerDTO() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
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

    public UUID getUserId() {
	return userId;
    }

    public void setUserId(UUID userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "NewCustomerDTO [name=" + name + ", lastName=" + lastName + ", address=" + address + ", cellPhone="
		+ cellPhone + ", email=" + email + ", userId=" + userId + "]";
    }

}
