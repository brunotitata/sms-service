package br.com.sms.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.sms.model.Active;

public class EmployeeDTO {

    private String nome;
    private String email;
    @Enumerated(EnumType.STRING)
    private Active active;
    private String userCpf;

    public EmployeeDTO(String nome, String email, Active active, String userCpf) {
	this.nome = nome;
	this.email = email;
	this.active = active;
	this.userCpf = userCpf;
    }

    public EmployeeDTO() {
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

    public Active getActive() {
	return active;
    }

    public void setActive(Active active) {
	this.active = active;
    }

    public String getUserCpf() {
	return userCpf;
    }

    public void setUserCpf(String userCpf) {
	this.userCpf = userCpf;
    }

    @Override
    public String toString() {
	return "EmployeeDTO [nome=" + nome + ", email=" + email + ", active=" + active + ", userCpf=" + userCpf + "]";
    }

}
