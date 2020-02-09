package br.com.sms.model;

import java.io.Serializable;

public class UserStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer quantidadeDeCreditoDisponivel;
    private Integer quantidadeTotalDeSms;
    private Integer quantidadeUltimoSetesDias;
    private Integer quantidadeUltimoQuinzeDias;
    private Integer quantidadeUltimoTrintaDias;

    public UserStatistics(Integer quantidadeDeCreditoDisponivel, Integer quantidadeTotalDeSms,
	    Integer quantidadeUltimoSetesDias, Integer quantidadeUltimoQuinzeDias, Integer quantidadeUltimoTrintaDias) {
	this.quantidadeDeCreditoDisponivel = quantidadeDeCreditoDisponivel;
	this.quantidadeTotalDeSms = quantidadeTotalDeSms;
	this.quantidadeUltimoSetesDias = quantidadeUltimoSetesDias;
	this.quantidadeUltimoQuinzeDias = quantidadeUltimoQuinzeDias;
	this.quantidadeUltimoTrintaDias = quantidadeUltimoTrintaDias;
    }

    @SuppressWarnings("unused")
    private UserStatistics() {
    }

    public Integer getQuantidadeDeCreditoDisponivel() {
	return quantidadeDeCreditoDisponivel;
    }

    public void setQuantidadeDeCreditoDisponivel(Integer quantidadeDeCreditoDisponivel) {
	this.quantidadeDeCreditoDisponivel = quantidadeDeCreditoDisponivel;
    }

    public Integer getQuantidadeTotalDeSms() {
	return quantidadeTotalDeSms;
    }

    public void setQuantidadeTotalDeSms(Integer quantidadeTotalDeSms) {
	this.quantidadeTotalDeSms = quantidadeTotalDeSms;
    }

    public Integer getQuantidadeUltimoSetesDias() {
	return quantidadeUltimoSetesDias;
    }

    public void setQuantidadeUltimoSetesDias(Integer quantidadeUltimoSetesDias) {
	this.quantidadeUltimoSetesDias = quantidadeUltimoSetesDias;
    }

    public Integer getQuantidadeUltimoQuinzeDias() {
	return quantidadeUltimoQuinzeDias;
    }

    public void setQuantidadeUltimoQuinzeDias(Integer quantidadeUltimoQuinzeDias) {
	this.quantidadeUltimoQuinzeDias = quantidadeUltimoQuinzeDias;
    }

    public Integer getQuantidadeUltimoTrintaDias() {
	return quantidadeUltimoTrintaDias;
    }

    public void setQuantidadeUltimoTrintaDias(Integer quantidadeUltimoTrintaDias) {
	this.quantidadeUltimoTrintaDias = quantidadeUltimoTrintaDias;
    }

    @Override
    public String toString() {
	return "UserStatistics [quantidadeDeCreditoDisponivel=" + quantidadeDeCreditoDisponivel
		+ ", quantidadeTotalDeSms=" + quantidadeTotalDeSms + ", quantidadeUltimoSetesDias="
		+ quantidadeUltimoSetesDias + ", quantidadeUltimoQuinzeDias=" + quantidadeUltimoQuinzeDias
		+ ", quantidadeUltimoTrintaDias=" + quantidadeUltimoTrintaDias + "]";
    }

}
