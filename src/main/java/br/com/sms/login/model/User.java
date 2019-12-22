package br.com.sms.login.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sms.login.util.Utils;
import br.com.sms.model.Customer;
import br.com.sms.model.SMS;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User implements Serializable {
    private static final long serialVersionUID = -4917125404604538056L;

    public static final String ERROR_INVALID_PASS = "Password can not be null or empty";
    public static final String ERROR_INVALID_NAME = "Name can not be null or empty";
    public static final String ERROR_INVALID_LAST_NAME = "Last name can not be null or empty";
    public static final String ERROR_INVALID_EMAIL = "Email can not be null or empty";
    public static final String ERROR_INVALID_ROLES = "Roles can not be null or empty";
    public static final String ERROR_INVALID_UUID = "UUID can not be null or empty";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID idUser;
    private String name;
    private String lastName;
    @Email(message = "Please provide a valid e-mail")
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private Set<Role> roles;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private Integer credit;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SMS> sms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Customer> customer;

    public User(String name, String lastName, String email, String password) {
	setName(name);
	setLastName(lastName);
	setEmail(email);
	setPassword(password);
    }

    public User(String name, String lastName, String email, String password, Set<Role> roles) {
	setName(name);
	setLastName(lastName);
	setEmail(email);
	setPassword(password);
	setRoles(roles);
    }

    public User() {
    }

    @PrePersist
    public void prePersist() {
	this.createdAt = LocalDate.now();
	this.credit = 0;
	this.idUser = UUID.randomUUID();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	Utils.argumentNotEmpty(name, ERROR_INVALID_NAME);
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	Utils.argumentNotEmpty(lastName, ERROR_INVALID_LAST_NAME);
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	Utils.argumentNotEmpty(email, ERROR_INVALID_EMAIL);
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	Utils.argumentNotEmpty(password, ERROR_INVALID_PASS);
	this.password = password;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	Utils.argumentNotNull(roles, ERROR_INVALID_ROLES);
	this.roles = roles;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	Utils.argumentNotNull(id, ERROR_INVALID_UUID);
	this.id = id;
    }

    public UUID getIdUser() {
	return idUser;
    }

    public Integer getCredit() {
	return credit;
    }

    public void setCredit(Integer credit) {
	this.credit = credit;
    }

    public LocalDate getCreatedAt() {
	return createdAt;
    }

    public List<SMS> getSms() {
	return sms;
    }

    public void setSms(List<SMS> sms) {
	this.sms = sms;
    }

    public boolean existCredit() {
	return this.credit <= 0;
    }

    public Integer debitCredits() {
	return this.credit - 1;
    }

    public List<Customer> getCustomer() {
	return customer;
    }

    public void setCustomer(List<Customer> customer) {
	this.customer = customer;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", idUser=" + idUser + ", name=" + name + ", lastName=" + lastName + ", email="
		+ email + ", password=" + password + ", roles=" + roles + ", createdAt=" + createdAt + ", credit="
		+ credit + ", sms=" + sms + "]";
    }

}
