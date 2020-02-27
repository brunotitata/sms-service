package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import br.com.sms.login.model.Role;
import br.com.sms.login.util.Utils;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    private static final long serialVersionUID = -1901541417432947200L;

    public static final String ERROR_ESTABELECIMENTO = "Estabelecimento não pode ser nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private UserId userId;

    private String nome;

    private String celular;

    @CPF(message = "CPF informado invalido.")
    private String cpf;

    private String email;

    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    @Enumerated(EnumType.STRING)
    private Active active;

    private Integer creditoDisponivel;

    private Integer quantidadeTotalDeSmsEnviado;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String mensagemPrefixo;

    public User(UserId clientId, String nome, String celular, String cpf, String email, String password,
	    Establishment establishment) {
	setUserId(clientId);
	setNome(nome);
	setCelular(celular);
	setCpf(cpf);
	setEmail(email);
	setPassword(password);
	setEstablishment(establishment);
    }

    public User() {
    }

    @PrePersist
    public void prePersist() {
	this.active = Active.ATIVO;
	this.creditoDisponivel = 0;
	this.role = Role.USER;
	this.quantidadeTotalDeSmsEnviado = 0;
    }

    public UserId getUserId() {
	return userId;
    }

    public void setUserId(UserId userId) {
	Utils.argumentNotNull(userId, UserId.ERROR_USER_ID);
	this.userId = userId;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getCelular() {
	return celular;
    }

    public void setCelular(String celular) {
	this.celular = celular;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
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
	Utils.argumentNotNull(establishment, ERROR_ESTABELECIMENTO);
	this.establishment = establishment;
    }

    public Active getActive() {
	return active;
    }

    public void setActive(Active active) {
	this.active = active;
    }

    public Integer getCreditoDisponivel() {
	return creditoDisponivel;
    }

    public void setCreditoDisponivel(Integer creditoDisponivel) {
	this.creditoDisponivel = creditoDisponivel;
    }

    public Integer getQuantidadeTotalDeSmsEnviado() {
	return quantidadeTotalDeSmsEnviado;
    }

    public void setQuantidadeTotalDeSmsEnviado(Integer quantidadeTotalDeSmsEnviado) {
	this.quantidadeTotalDeSmsEnviado = quantidadeTotalDeSmsEnviado;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public UUID getId() {
	return id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Integer smsCounter() {
	return this.quantidadeTotalDeSmsEnviado + 1;
    }

    public Integer creditAvailable() {
	return this.creditoDisponivel - 1;
    }

    public String getMensagemPrefixo() {
	return mensagemPrefixo;
    }

    public void setMensagemPrefixo(String mensagemPrefixo) {
	this.mensagemPrefixo = mensagemPrefixo;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", userId=" + userId + ", nome=" + nome + ", celular=" + celular + ", cpf=" + cpf
		+ ", email=" + email + ", password=" + password + ", establishment=" + establishment + ", active="
		+ active + ", creditoDisponivel=" + creditoDisponivel + ", quantidadeTotalDeSmsEnviado="
		+ quantidadeTotalDeSmsEnviado + ", role=" + role + ", mensagemPrefixo=" + mensagemPrefixo + "]";
    }

}
