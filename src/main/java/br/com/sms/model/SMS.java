package br.com.sms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SMS implements Serializable {
    private static final long serialVersionUID = -2969899889965050481L;

    public static final String ERROR_INVALID_NUMBER_PHONE = "Numero de telefone não pode ser vazio ou nulo.";
    public static final String ERROR_INVALID_BODY = "Mensagem a ser enviado não pode ser vazio ou nulo.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private SmsId smsId;
    private String nomeFuncionario;
    private String messagem;
    private String numero;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    public SMS(SmsId smsId, String nomeFuncionario, String messagem, String numero, LocalDateTime localDateTime) {
	setSmsId(smsId);
	setNomeFuncionario(nomeFuncionario);
	setMessagem(messagem);
	setNumero(numero);
	setLocalDateTime(localDateTime);
    }

    @SuppressWarnings("unused")
    private SMS() {
    }

    public UUID getId() {
	return id;
    }

    public SmsId getSmsId() {
	return smsId;
    }

    public void setSmsId(SmsId smsId) {
	this.smsId = smsId;
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

    public LocalDateTime getLocalDateTime() {
	return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
	this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
	return "SMS [id=" + id + ", smsId=" + smsId + ", nomeFuncionario=" + nomeFuncionario + ", messagem=" + messagem
		+ ", numero=" + numero + ", localDateTime=" + localDateTime + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
	result = prime * result + ((messagem == null) ? 0 : messagem.hashCode());
	result = prime * result + ((nomeFuncionario == null) ? 0 : nomeFuncionario.hashCode());
	result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
	SMS other = (SMS) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (localDateTime == null) {
	    if (other.localDateTime != null)
		return false;
	} else if (!localDateTime.equals(other.localDateTime))
	    return false;
	if (messagem == null) {
	    if (other.messagem != null)
		return false;
	} else if (!messagem.equals(other.messagem))
	    return false;
	if (nomeFuncionario == null) {
	    if (other.nomeFuncionario != null)
		return false;
	} else if (!nomeFuncionario.equals(other.nomeFuncionario))
	    return false;
	if (numero == null) {
	    if (other.numero != null)
		return false;
	} else if (!numero.equals(other.numero))
	    return false;
	if (smsId == null) {
	    if (other.smsId != null)
		return false;
	} else if (!smsId.equals(other.smsId))
	    return false;
	return true;
    }

}