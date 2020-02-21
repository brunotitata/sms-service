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

    @CPF
    private String cpf;
    private String email;

    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    @Enumerated(EnumType.STRING)
    private Active active;
    private Integer creditoDisponivel;
    private Integer creditoContratado;
    private Integer quantidadeTotalDeSmsEnviado;

    @Enumerated(EnumType.STRING)
    private Role role;

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
	this.creditoContratado = 0;
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

    public Integer getCreditoContratado() {
	return creditoContratado;
    }

    public void setCreditoContratado(Integer creditoContratado) {
	this.creditoContratado = creditoContratado;
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

    @Override
    public String toString() {
	return "Client [id=" + id + ", userId=" + userId + ", nome=" + nome + ", celular=" + celular + ", cpf=" + cpf
		+ ", email=" + email + ", password=" + password + ", establishment=" + establishment + ", active="
		+ active + ", creditoDisponivel=" + creditoDisponivel + ", creditoContratado=" + creditoContratado
		+ ", quantidadeTotalDeSmsEnviado=" + quantidadeTotalDeSmsEnviado + ", role=" + role + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((active == null) ? 0 : active.hashCode());
	result = prime * result + ((celular == null) ? 0 : celular.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	result = prime * result + ((creditoContratado == null) ? 0 : creditoContratado.hashCode());
	result = prime * result + ((creditoDisponivel == null) ? 0 : creditoDisponivel.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((establishment == null) ? 0 : establishment.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((quantidadeTotalDeSmsEnviado == null) ? 0 : quantidadeTotalDeSmsEnviado.hashCode());
	result = prime * result + ((role == null) ? 0 : role.hashCode());
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
	User other = (User) obj;
	if (active != other.active)
	    return false;
	if (celular == null) {
	    if (other.celular != null)
		return false;
	} else if (!celular.equals(other.celular))
	    return false;
	if (userId == null) {
	    if (other.userId != null)
		return false;
	} else if (!userId.equals(other.userId))
	    return false;
	if (cpf == null) {
	    if (other.cpf != null)
		return false;
	} else if (!cpf.equals(other.cpf))
	    return false;
	if (creditoContratado == null) {
	    if (other.creditoContratado != null)
		return false;
	} else if (!creditoContratado.equals(other.creditoContratado))
	    return false;
	if (creditoDisponivel == null) {
	    if (other.creditoDisponivel != null)
		return false;
	} else if (!creditoDisponivel.equals(other.creditoDisponivel))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (establishment == null) {
	    if (other.establishment != null)
		return false;
	} else if (!establishment.equals(other.establishment))
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
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (quantidadeTotalDeSmsEnviado == null) {
	    if (other.quantidadeTotalDeSmsEnviado != null)
		return false;
	} else if (!quantidadeTotalDeSmsEnviado.equals(other.quantidadeTotalDeSmsEnviado))
	    return false;
	if (role != other.role)
	    return false;
	return true;
    }

}
