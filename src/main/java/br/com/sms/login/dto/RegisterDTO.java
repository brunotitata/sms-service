package br.com.sms.login.dto;

import java.util.Set;

import javax.validation.constraints.Email;

public class RegisterDTO {

    private String name;
    private String establishment;
    @Email
    private String email;
    private Set<String> role;
    private String password;
    private Integer credit;
    private Boolean active;

    public RegisterDTO(String name, String establishment, @Email String email, Set<String> role, String password,
	    Integer credit, Boolean active) {
	this.name = name;
	this.establishment = establishment;
	this.email = email;
	this.role = role;
	this.password = password;
	this.credit = credit;
	this.active = active;
    }

    public RegisterDTO() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEstablishment() {
	return establishment;
    }

    public void setEstablishment(String establishment) {
	this.establishment = establishment;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Set<String> getRole() {
	return this.role;
    }

    public void setRole(Set<String> role) {
	this.role = role;
    }

    public Integer getCredit() {
	return credit;
    }

    public void setCredit(Integer credit) {
	this.credit = credit;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    @Override
    public String toString() {
	return "RegisterDTO [name=" + name + ", establishment=" + establishment + ", email=" + email + ", role=" + role
		+ ", password=" + password + ", credit=" + credit + ", active=" + active + "]";
    }

}
