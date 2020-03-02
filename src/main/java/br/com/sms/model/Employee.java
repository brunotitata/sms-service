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
    private Integer quantidadeDeSmsEnviado = 0;

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

//    public Establishment getEstablishment() {
//	return establishment;
//    }
//
//    public void setEstablishment(Establishment establishment) {
//	this.establishment = establishment;
//    }

    public Integer smsCounter() {
	return this.quantidadeDeSmsEnviado + 1;
    }

    @Override
    public String toString() {
	return "Employee [id=" + id + ", employeeId=" + employeeId + ", nome=" + nome + ", email=" + email + ", senha="
		+ senha + ", active=" + active + ", quantidadeDeSmsEnviado=" + quantidadeDeSmsEnviado + "]";
    }

}