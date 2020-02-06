package br.com.sms.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.sms.dto.CustomerDTO;
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
    private String cellPhone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SMS> sms;

    public Customer(String name, String cellPhone, String email, User user) {
	setName(name);
	setCellPhone(cellPhone);
	setEmail(email);
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

    public String getCellPhone() {
	return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
	Utils.argumentNotEmpty(cellPhone, ERROR_INVALID_CELLPHONE);
	this.cellPhone = cellPhone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public UUID getId() {
	return id;
    }

    public void setUser(User user) {
	Utils.argumentNotNull(user, ERROR_INVALID_USER);
	this.user = user;
    }

    public static CustomerDTO convertToDto(Customer customer) {
	return new CustomerDTO(customer.getName(), customer.getCellPhone(), customer.getEmail());
    }

    @Override
    public String toString() {
	return "Customer [id=" + id + ", name=" + name + ", cellPhone=" + cellPhone + ", email=" + email + "]";
    }

}
