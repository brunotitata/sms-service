package br.com.sms.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EstablishmentId establishmentId;
    private String nome;
    private String endereco;
    @CNPJ
    private String cnpj;

    @OneToMany(mappedBy = "establishment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @OneToMany(mappedBy = "establishment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SMS> sms;

    @OneToMany(mappedBy = "establishment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Customer> customer;

    @OneToOne(mappedBy = "establishment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public Establishment(EstablishmentId establishmentId, String nome, String endereco, String cnpj,
	    Set<Employee> employee, List<SMS> sms, Set<Customer> customer) {
	this.establishmentId = establishmentId;
	this.nome = nome;
	this.endereco = endereco;
	this.cnpj = cnpj;
	this.employee = employee;
	this.sms = sms;
	this.customer = customer;
    }

    public Establishment() {
    }

    public EstablishmentId getEstablishmentId() {
	return establishmentId;
    }

    public void setEstablishmentId(EstablishmentId establishmentId) {
	this.establishmentId = establishmentId;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
    }

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public Set<Employee> getEmployee() {
	return employee;
    }

    public void setEmployee(Set<Employee> employee) {
	this.employee = employee;
    }

    public List<SMS> getSms() {
	return sms;
    }

    public void setSms(List<SMS> sms) {
	this.sms = sms;
    }

    public Set<Customer> getCustomer() {
	return customer;
    }

    public void setCustomer(Set<Customer> customer) {
	this.customer = customer;
    }

    public UUID getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Establishment [id=" + id + ", establishmentId=" + establishmentId + ", nome=" + nome + ", endereco="
		+ endereco + ", cnpj=" + cnpj + ", funcionarios=" + employee + ", sms=" + sms + ", customer=" + customer
		+ "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
	result = prime * result + ((customer == null) ? 0 : customer.hashCode());
	result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
	result = prime * result + ((establishmentId == null) ? 0 : establishmentId.hashCode());
	result = prime * result + ((employee == null) ? 0 : employee.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((sms == null) ? 0 : sms.hashCode());
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
	Establishment other = (Establishment) obj;
	if (cnpj == null) {
	    if (other.cnpj != null)
		return false;
	} else if (!cnpj.equals(other.cnpj))
	    return false;
	if (customer == null) {
	    if (other.customer != null)
		return false;
	} else if (!customer.equals(other.customer))
	    return false;
	if (endereco == null) {
	    if (other.endereco != null)
		return false;
	} else if (!endereco.equals(other.endereco))
	    return false;
	if (establishmentId == null) {
	    if (other.establishmentId != null)
		return false;
	} else if (!establishmentId.equals(other.establishmentId))
	    return false;
	if (employee == null) {
	    if (other.employee != null)
		return false;
	} else if (!employee.equals(other.employee))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (sms == null) {
	    if (other.sms != null)
		return false;
	} else if (!sms.equals(other.sms))
	    return false;
	return true;
    }

}
