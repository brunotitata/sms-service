package br.com.sms.login.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sms.model.User;

public class UserDTO implements UserDetails {
    private static final long serialVersionUID = -5745731685321252631L;

    private UUID userId;
    private String name;
    private String email;
    private String establishment;
    private Integer credit;
    private Integer smsCounter;
    private String password;
    private String cpf;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDTO(UUID userId, String name, String email, String establishment, Integer credit, Integer smsCounter,
	    String password, String cpf, Collection<? extends GrantedAuthority> authorities) {
	this.userId = userId;
	this.name = name;
	this.email = email;
	this.establishment = establishment;
	this.credit = credit;
	this.smsCounter = smsCounter;
	this.password = password;
	this.cpf = cpf;
	this.authorities = authorities;
    }

    public static UserDTO build(User user) {
	List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()));

	return new UserDTO(user.getUserId().getId(), user.getNome(), user.getEmail(), user.getEstablishment().getNome(),
		user.getCreditoDisponivel(), user.getQuantidadeTotalDeSmsEnviado(), user.getPassword(), user.getCpf(),
		authorities);
    }

    @Override
    public String getUsername() {
	return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return authorities;
    }

    public Integer getCredit() {
	return credit;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    public UUID getUserId() {
	return userId;
    }

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
    }

    public Integer getSmsCounter() {
	return smsCounter;
    }

    public String getEstablishment() {
	return establishment;
    }

    public String getCpf() {
	return cpf;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String toString() {
	return "UserDTO [userId=" + userId + ", name=" + name + ", email=" + email + ", establishment=" + establishment
		+ ", credit=" + credit + ", smsCounter=" + smsCounter + ", authorities=" + authorities + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
	result = prime * result + ((credit == null) ? 0 : credit.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((establishment == null) ? 0 : establishment.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((smsCounter == null) ? 0 : smsCounter.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
	UserDTO other = (UserDTO) obj;
	if (authorities == null) {
	    if (other.authorities != null)
		return false;
	} else if (!authorities.equals(other.authorities))
	    return false;
	if (credit == null) {
	    if (other.credit != null)
		return false;
	} else if (!credit.equals(other.credit))
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
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (smsCounter == null) {
	    if (other.smsCounter != null)
		return false;
	} else if (!smsCounter.equals(other.smsCounter))
	    return false;
	if (userId == null) {
	    if (other.userId != null)
		return false;
	} else if (!userId.equals(other.userId))
	    return false;
	return true;
    }

}
