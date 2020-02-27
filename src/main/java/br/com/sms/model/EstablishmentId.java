package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstablishmentId implements Serializable {
    private static final long serialVersionUID = 2365534913716156506L;

    @Column(name = "ESTABLISHMENT_ID", columnDefinition = "uuid")
    private UUID id;

    public EstablishmentId(UUID establishmentId) {
	this.id = establishmentId;
    }

    @SuppressWarnings("unused")
    private EstablishmentId() {
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return "EstablishmentId [id=" + id + "]";
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
	EstablishmentId other = (EstablishmentId) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}