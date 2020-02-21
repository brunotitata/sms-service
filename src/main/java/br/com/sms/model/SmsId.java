package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class SmsId implements Serializable {
    private static final long serialVersionUID = -4323050393290398954L;

    @Column(name = "SMS_ID", columnDefinition = "uuid")
    private UUID smsId;

    public SmsId(UUID smsId) {
	setSmsId(smsId);
    }

    @SuppressWarnings("unused")
    private SmsId() {
    }

    public UUID getSmsId() {
	return smsId;
    }

    public void setSmsId(UUID smsId) {
	this.smsId = smsId;
    }

    @Override
    public String toString() {
	return "SmsId [smsId=" + smsId + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((smsId == null) ? 0 : smsId.hashCode());
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
	SmsId other = (SmsId) obj;
	if (smsId == null) {
	    if (other.smsId != null)
		return false;
	} else if (!smsId.equals(other.smsId))
	    return false;
	return true;
    }

}