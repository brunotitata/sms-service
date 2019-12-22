package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sms.login.model.User;
import br.com.sms.login.util.Utils;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = -8281283451700573518L;

    public static final String ERROR_INVALID_NAME = "Nome do cliente não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_LASTNAME = "Sobrenome do cliente não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_ADDRESS = "Endereço do cliente não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_CELLPHONE = "Numero celular não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_USER = "Usuario não pode ser vazio ou nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String lastName;
    private String address;
    private String cellPhone;
    private String telephone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Customer(String name, String lastName, String address, String cellPhone, String telephone, User user) {
	setName(name);
	setLastName(lastName);
	setAddress(address);
	setCellPhone(cellPhone);
	setTelephone(telephone);
	setUser(user);
    }

    public Customer() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	Utils.argumentNotEmpty(name, ERROR_INVALID_NAME);
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	Utils.argumentNotEmpty(lastName, ERROR_INVALID_LASTNAME);
	this.lastName = lastName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	Utils.argumentNotEmpty(address, ERROR_INVALID_ADDRESS);
	this.address = address;
    }

    public String getCellPhone() {
	return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
	Utils.argumentNotEmpty(cellPhone, ERROR_INVALID_CELLPHONE);
	this.cellPhone = cellPhone;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public UUID getId() {
	return id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	Utils.argumentNotNull(user, ERROR_INVALID_USER);
	this.user = user;
    }

    @Override
    public String toString() {
	return "Customer [id=" + id + ", name=" + name + ", lastName=" + lastName + ", address=" + address
		+ ", cellPhone=" + cellPhone + ", telephone=" + telephone + ", user=" + user + "]";
    }

}
