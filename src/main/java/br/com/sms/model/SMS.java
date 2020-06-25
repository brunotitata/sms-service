package br.com.sms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sms.dto.SmsSpecificationDTO;

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
    @Column(name = "createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private String awsMessageId;
    private String status;
    private String messageError;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    public SMS(SmsId smsId, String nomeFuncionario, String messagem, String numero, LocalDateTime localDateTime,
               String awsMessageId, String status, String messageError, Establishment establishment) {
        setSmsId(smsId);
        setNomeFuncionario(nomeFuncionario);
        setMessagem(messagem);
        setNumero(numero);
        setLocalDateTime(localDateTime);
        setAwsMessageId(awsMessageId);
        setStatus(status);
        setMessageError(messageError);
        this.establishment = establishment;
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

//    public Establishment getEstablishment() {
//	return establishment;
//    }
//
//    public void setEstablishment(Establishment establishment) {
//	this.establishment = establishment;
//    }

    public String getAwsMessageId() {
        return awsMessageId;
    }

    public void setAwsMessageId(String awsMessageId) {
        this.awsMessageId = awsMessageId;
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

    public static SmsSpecificationDTO convertToDTO(SMS sms) {
        return new SmsSpecificationDTO(sms.getNomeFuncionario(), sms.getMessagem(), sms.getNumero(), sms.getLocalDateTime(),
                sms.getStatus(), sms.getMessageError());
    }

    @Override
    public String toString() {
        return "SMS [id=" + id + ", smsId=" + smsId + ", nomeFuncionario=" + nomeFuncionario + ", messagem=" + messagem
                + ", numero=" + numero + ", localDateTime=" + localDateTime + ", awsMessageId=" + awsMessageId
                + ", status=" + status + ", messageError=" + messageError + "]";
    }

}