package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstablishmentId implements Serializable {
    private static final long serialVersionUID = 2365534913716156506L;

    @Column(name = "ESTABLISHMENT_ID", columnDefinition = "uuid")
    private UUID establishmentId;

    public EstablishmentId(UUID establishmentId) {
	this.establishmentId = establishmentId;
    }

    @SuppressWarnings("unused")
    private EstablishmentId() {
    }

    public UUID getEstablishmentId() {
	return establishmentId;
    }

    public void setEstablishmentId(UUID establishmentId) {
	this.establishmentId = establishmentId;
    }

    @Override
    public String toString() {
	return "EstablishmentId [establishmentId=" + establishmentId + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((establishmentId == null) ? 0 : establishmentId.hashCode());
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
	if (establishmentId == null) {
	    if (other.establishmentId != null)
		return false;
	} else if (!establishmentId.equals(other.establishmentId))
	    return false;
	return true;
    }

}