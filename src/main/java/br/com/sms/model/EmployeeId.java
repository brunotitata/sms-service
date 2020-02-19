package br.com.sms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class EmployeeId implements Serializable {
    private static final long serialVersionUID = 1619388780955822378L;

    @Column(name = "EMPLOYEE_ID", columnDefinition = "uuid")
    private UUID employeeId;

    public EmployeeId(UUID employeeId) {
	this.employeeId = employeeId;
    }

    @SuppressWarnings("unused")
    private EmployeeId() {
    }

    public UUID getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
	this.employeeId = employeeId;
    }

    @Override
    public String toString() {
	return "EmployeeId [employeeId=" + employeeId + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
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
	EmployeeId other = (EmployeeId) obj;
	if (employeeId == null) {
	    if (other.employeeId != null)
		return false;
	} else if (!employeeId.equals(other.employeeId))
	    return false;
	return true;
    }

}
