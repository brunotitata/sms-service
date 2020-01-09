package br.com.sms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sms.login.model.User;
import br.com.sms.login.util.Utils;

@Entity
public class SMS implements Serializable {
    private static final long serialVersionUID = -2969899889965050481L;

    public static final String ERROR_INVALID_NUMBER_PHONE = "Numero de telefone não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_BODY = "Mensagem a ser enviado não pode ser vazio ou nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String numberPhone;
    private String body;
    private String statusServiceApi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SMS() {
    }

    public SMS(String numberPhone, String body, String statusServiceApi, User user) {
	setNumberPhone(numberPhone);
	setBody(body);
	this.statusServiceApi = statusServiceApi;
	this.user = user;
    }

    @PrePersist
    public void prePersist() {
	this.createdAt = LocalDateTime.now();
    }

    public String getNumberPhone() {
	return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
	Utils.argumentNotEmpty(numberPhone, ERROR_INVALID_NUMBER_PHONE);
	this.numberPhone = numberPhone;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	Utils.argumentNotEmpty(body, ERROR_INVALID_BODY);
	this.body = body;
    }

    public String getStatusServiceApi() {
	return statusServiceApi;
    }

    public void setStatusServiceApi(String statusServiceApi) {
	this.statusServiceApi = statusServiceApi;
    }

    public LocalDateTime getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
    }

    public UUID getId() {
	return id;
    }

    @Override
    public String toString() {
	return "SMS [id=" + id + ", numberPhone=" + numberPhone + ", body=" + body + ", statusServiceApi="
		+ statusServiceApi + ", createdAt=" + createdAt + ", user=" + user + "]";
    }

}
