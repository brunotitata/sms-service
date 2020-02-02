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

    public RegisterDTO(String name, String establishment, @Email String email, Set<String> role, String password) {
	this.name = name;
	this.establishment = establishment;
	this.email = email;
	this.role = role;
	this.password = password;
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

    @Override
    public String toString() {
	return "RegisterData [name=" + name + ", establishment=" + establishment + ", email=" + email + ", role=" + role
		+ ", password=" + password + "]";
    }

}
