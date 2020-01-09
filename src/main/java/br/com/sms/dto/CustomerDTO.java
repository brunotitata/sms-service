package br.com.sms.dto;

public class CustomerDTO {

    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String telephone;

    public CustomerDTO(String name, String lastName, String address, String cellPhone, String telephone) {
	this.name = name;
	this.lastName = lastName;
	this.address = address;
	this.cellPhone = cellPhone;
	this.telephone = telephone;
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

    @Override
    public String toString() {
	return "CustomerDTO [name=" + name + ", lastName=" + lastName + ", address=" + address + ", cellPhone="
		+ cellPhone + ", telephone=" + telephone + "]";
    }

}
