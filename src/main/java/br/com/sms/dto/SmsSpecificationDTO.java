package br.com.sms.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SmsSpecificationDTO {

    private String nomeFuncionario;
    private String messagem;
    private String numero;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private String status;
    private String messageError;

    public SmsSpecificationDTO(String nomeFuncionario, String messagem, String numero, LocalDateTime createdAt, String status,
	    String messageError) {
	this.nomeFuncionario = nomeFuncionario;
	this.messagem = messagem;
	this.numero = numero;
	this.createdAt = createdAt;
	this.status = status;
	this.messageError = messageError;
    }

    public SmsSpecificationDTO() {
    }

    public String getNomeFuncionario() {
	return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
	this.nomeFuncionario = nomeFuncionario;
    }

    public String getMessagem() {
	return messagem;
    }

    public void setMessagem(String messagem) {
	this.messagem = messagem;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public LocalDateTime getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getMessageError() {
	return messageError;
    }

    public void setMessageError(String messageError) {
	this.messageError = messageError;
    }

    @Override
    public String toString() {
	return "SmsDTO [nomeFuncionario=" + nomeFuncionario + ", messagem=" + messagem + ", numero=" + numero
		+ ", createdAt=" + createdAt + ", status=" + status + ", messageError=" + messageError + "]";
    }

}
