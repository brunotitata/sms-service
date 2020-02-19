package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class CustomerId implements Serializable {
    private static final long serialVersionUID = 4079892059742255575L;

    @Column(name = "CUSTOMER_ID", columnDefinition = "uuid")
    private UUID id;

    public CustomerId(UUID customerId) {
	setId(customerId);
    }

    @SuppressWarnings("unused")
    private CustomerId() {
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return "CustomerId [id=" + id + "]";
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
	CustomerId other = (CustomerId) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
