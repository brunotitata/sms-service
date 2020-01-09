package br.com.sms.dto;

import java.util.UUID;

public class CustomerDTO {

    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String telephone;
    private UUID userId;

    public CustomerDTO(String name, String lastName, String address, String cellPhone, String telephone, UUID userId) {
	this.name = name;
	this.lastName = lastName;
	this.address = address;
	this.cellPhone = cellPhone;
	this.telephone = telephone;
	this.userId = userId;
    }

    public CustomerDTO() {
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

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public UUID getUserId() {
	return userId;
    }

    public void setUserId(UUID userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "CustomerDTO [name=" + name + ", lastName=" + lastName + ", address=" + address + ", cellPhone="
		+ cellPhone + ", telephone=" + telephone + ", userId=" + userId + "]";
    }

}
