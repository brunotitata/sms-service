package br.com.sms.login.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = -1994895363715782668L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
	ROLE_ADMIN, ROLE_USER;
    }

    public Role() {
    }

    public Role(RoleName name) {
	this.name = name;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public RoleName getName() {
	return name;
    }

    public void setName(RoleName name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "Role [id=" + id + ", name=" + name + "]";
    }

}
