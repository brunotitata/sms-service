package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
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

    public Customer(CustomerId customerId, String name, String cellPhone, String email, Establishment establishment) {
	this.customerId = customerId;
	this.name = name;
	this.cellPhone = cellPhone;
	this.email = email;
	this.establishment = establishment;
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

    public Establishment getEstablishment() {
	return establishment;
    }

    public void setEstablishment(Establishment establishment) {
	this.establishment = establishment;
    }

    public static CustomerDTO convertToDto(Customer customer) {
	return new CustomerDTO(customer.getName(), customer.getCellPhone(), customer.getEmail());
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
	return "Customer [id=" + id + ", customerId=" + customerId + ", name=" + name + ", cellPhone=" + cellPhone
		+ ", email=" + email + ", quantityOfSmsSent=" + quantityOfSmsSent + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
	result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Customer other = (Customer) obj;
	if (cellPhone == null) {
	    if (other.cellPhone != null)
		return false;
	} else if (!cellPhone.equals(other.cellPhone))
	    return false;
	if (customerId == null) {
	    if (other.customerId != null)
		return false;
	} else if (!customerId.equals(other.customerId))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}