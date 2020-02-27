package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.sms.login.util.Utils;

@Embeddable
@Access(AccessType.FIELD)
public class UserId implements Serializable {
    private static final long serialVersionUID = 625614282738652897L;

    public static final String ERROR_USER_ID = "UserID não pode ser nulo ou vazio.";

    @Column(name = "USER_ID", columnDefinition = "uuid")
    private UUID id;

    public UserId(UUID id) {
	this.id = id;
    }

    @SuppressWarnings("unused")
    private UserId() {
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	Utils.argumentNotNull(id, ERROR_USER_ID);
	this.id = id;
    }

    @Override
    public String toString() {
	return "UserId [id=" + id + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	UserId other = (UserId) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
