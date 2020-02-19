//package br.com.sms.login.model;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.PrePersist;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.Email;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import br.com.sms.login.util.Utils;
//import br.com.sms.model.Customer;
//
//@Entity
//@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
//public class User implements Serializable {
//    private static final long serialVersionUID = -4917125404604538056L;
//
//    public static final String ERROR_INVALID_PASS = "Password can not be null or empty";
//    public static final String ERROR_INVALID_NAME = "Name can not be null or empty";
//    public static final String ERROR_INVALID_ESTABLISHMENT = "Establishment can not be null or empty";
//    public static final String ERROR_INVALID_EMAIL = "Email can not be null or empty";
//    public static final String ERROR_INVALID_ROLES = "Roles can not be null or empty";
//    public static final String ERROR_INVALID_UUID = "UUID can not be null or empty";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//    private String name;
//    private String establishment;
//
//    @Email(message = "Please provide a valid e-mail")
//    private String email;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Set<Role> roles;
//
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate createdAt;
//
//    private Integer credit;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Fetch(value = FetchMode.SUBSELECT)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Customer> customer;
//
//    private Integer counterSms;
//
//    private Boolean active;
//
//    public User(String name, String establishment, String email, String password, Integer credit, Boolean active) {
//	setName(name);
//	setEstablishment(establishment);
//	setEmail(email);
//	setPassword(password);
//	setCredit(credit);
//	setActive(active);
//    }
//
//    public User() {
//    }
//
//    @PrePersist
//    public void prePersist() {
//	this.createdAt = LocalDate.now();
//	this.counterSms = 0;
//    }
//
//    public String getName() {
//	return name;
//    }
//
//    public void setName(String name) {
//	Utils.argumentNotEmpty(name, ERROR_INVALID_NAME);
//	this.name = name;
//    }
//
//    public String getEstablishment() {
//	return establishment;
//    }
//
//    public void setEstablishment(String establishment) {
//	Utils.argumentNotEmpty(establishment, ERROR_INVALID_ESTABLISHMENT);
//	this.establishment = establishment;
//    }
//
//    public String getEmail() {
//	return email;
//    }
//
//    public void setEmail(String email) {
//	Utils.argumentNotEmpty(email, ERROR_INVALID_EMAIL);
//	this.email = email;
//    }
//
//    public String getPassword() {
//	return password;
//    }
//
//    public void setPassword(String password) {
//	Utils.argumentNotEmpty(password, ERROR_INVALID_PASS);
//	this.password = password;
//    }
//
//    public Set<Role> getRoles() {
//	return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//	Utils.argumentNotNull(roles, ERROR_INVALID_ROLES);
//	this.roles = roles;
//    }
//
//    public UUID getId() {
//	return id;
//    }
//
//    public Integer getCredit() {
//	return credit;
//    }
//
//    public void setCredit(Integer credit) {
//	this.credit = credit;
//    }
//
//    public LocalDate getCreatedAt() {
//	return createdAt;
//    }
//
//    public List<Customer> getCustomer() {
//	return customer;
//    }
//
//    public void setCustomer(List<Customer> customer) {
//	this.customer = customer;
//    }
//
//    public Integer getCounterSms() {
//	return counterSms;
//    }
//
//    public void setCounterSms(Integer counterSms) {
//	this.counterSms = counterSms;
//    }
//
//    public boolean checkForCredits() {
//	return this.credit <= 0;
//    }
//
//    public Integer debitCredits() {
//	return this.credit - 1;
//    }
//
//    public Integer smsCounter() {
//	return this.counterSms + 1;
//    }
//
//    public Boolean getActive() {
//	return active;
//    }
//
//    public void setActive(Boolean active) {
//	this.active = active;
//    }
//
//    @Override
//    public String toString() {
//	return "User [id=" + id + ", name=" + name + ", establishment=" + establishment + ", email=" + email
//		+ ", password=" + password + ", roles=" + roles + ", createdAt=" + createdAt + ", credit=" + credit
//		+ ", counterSms=" + counterSms + ", active=" + active + "]";
//    }
//
//}
