package br.com.sms.model;

import java.io.Serializable;

public class UserStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer credit;
    private Integer counterSms;

    public UserStatistics(Integer credit, Integer counterSms) {
	this.credit = credit;
	this.counterSms = counterSms;
    }

    @SuppressWarnings("unused")
    private UserStatistics() {
    }

    public Integer getCredit() {
	return credit;
    }

    public void setCredit(Integer credit) {
	this.credit = credit;
    }

    public Integer getCounterSms() {
	return counterSms;
    }

    public void setCounterSms(Integer counterSms) {
	this.counterSms = counterSms;
    }

    @Override
    public String toString() {
	return "UserStatistics [credit=" + credit + ", counterSms=" + counterSms + "]";
    }

}
