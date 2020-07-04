package br.com.sms.dto;

public class CreditDTO {

    private String userId;
    private String quantity;

    public CreditDTO(String userId, String quantity) {
	this.userId = userId;
	this.quantity = quantity;
    }

    public CreditDTO() {
	// TODO Auto-generated constructor stub
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getQuantity() {
	return quantity;
    }

    public void setQuantity(String quantity) {
	this.quantity = quantity;
    }

    @Override
    public String toString() {
	return "CreditDTO [userId=" + userId + ", quantity=" + quantity + "]";
    }

}
