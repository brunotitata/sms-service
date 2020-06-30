package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.sms.dto.CustomerDTO;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = -8281283451700573518L;

    public static final String ERROR_INVALID_NAME = "Nome do cliente não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_CELLPHONE = "Numero celular não pode ser vazio ou nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private CustomerId customerId;
    private String name;
    private String cellPhone;
    private String email;

    private Integer quantityOfSmsSent = 0;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    @Enumerated(EnumType.STRING)
    private Active active;

    public Customer(CustomerId customerId, String name, String cellPhone, String email, Establishment establishment,
	    Active active) {
	this.customerId = customerId;
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
	this.establishment = establishment;
	this.active = active;
    }

    @SuppressWarnings("unused")
    private Customer() {
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public CustomerId getCustomerId() {
	return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
	this.customerId = customerId;
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

    public Active getActive() {
	return active;
    }

    public void setActive(Active active) {
	this.active = active;
    }

    public static CustomerDTO convertToDto(Customer customer) {
	return new CustomerDTO(customer.getName(), customer.getCellPhone(), customer.getEmail(),
		customer.getQuantityOfSmsSent(), customer.getActive() == Active.ATIVO ? true : false);
    }

    public Integer getQuantityOfSmsSent() {
	return quantityOfSmsSent;
    }

    public void setQuantityOfSmsSent(Integer quantityOfSmsSent) {
	this.quantityOfSmsSent = quantityOfSmsSent;
    }

    public Integer counterSms() {
	return this.quantityOfSmsSent + 1;
    }

    @Override
    public String toString() {
	return "Customer [customerId=" + customerId + ", name=" + name + ", cellPhone=" + cellPhone + ", email=" + email
		+ ", quantityOfSmsSent=" + quantityOfSmsSent + ", active=" + active + "]";
    }

}
