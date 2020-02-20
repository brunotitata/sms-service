package br.com.sms.model;

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

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmployeeId employeeId;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Active active;
    private Integer quantidadeDeSmsEnviado;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    public Employee(EmployeeId employeeId, String nome, String email, String senha, Active active,
	    Establishment establishment) {
	this.employeeId = employeeId;
	this.nome = nome;
	this.email = email;
	this.senha = senha;
	this.active = active;
	this.establishment = establishment;
    }

    @SuppressWarnings("unused")
    private Employee() {
    }

    public EmployeeId getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(EmployeeId employeeId) {
	this.employeeId = employeeId;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public Active getActive() {
	return active;
    }

    public void setActive(Active active) {
	this.active = active;
    }

    public Integer getQuantidadeDeSmsEnviado() {
	return quantidadeDeSmsEnviado;
    }

    public void setQuantidadeDeSmsEnviado(Integer quantidadeDeSmsEnviado) {
	this.quantidadeDeSmsEnviado = quantidadeDeSmsEnviado;
    }

    public UUID getId() {
	return id;
    }

    public Establishment getEstablishment() {
	return establishment;
    }

    public void setEstablishment(Establishment establishment) {
	this.establishment = establishment;
    }

    public Integer smsCounter() {
	return this.quantidadeDeSmsEnviado = +1;
    }

    @Override
    public String toString() {
	return "Employee [id=" + id + ", employeeId=" + employeeId + ", nome=" + nome + ", email=" + email + ", senha="
		+ senha + ", active=" + active + ", quantidadeDeSmsEnviado=" + quantidadeDeSmsEnviado + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((active == null) ? 0 : active.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((quantidadeDeSmsEnviado == null) ? 0 : quantidadeDeSmsEnviado.hashCode());
	result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
	Employee other = (Employee) obj;
	if (active != other.active)
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (employeeId == null) {
	    if (other.employeeId != null)
		return false;
	} else if (!employeeId.equals(other.employeeId))
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
	if (quantidadeDeSmsEnviado == null) {
	    if (other.quantidadeDeSmsEnviado != null)
		return false;
	} else if (!quantidadeDeSmsEnviado.equals(other.quantidadeDeSmsEnviado))
	    return false;
	if (senha == null) {
	    if (other.senha != null)
		return false;
	} else if (!senha.equals(other.senha))
	    return false;
	return true;
    }

}