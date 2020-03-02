package br.com.sms.dto;

public class UserDTO {

    private String nome;
    private String celular;
    private String cpf;
    private String email;
    private EstablishmentDTO establishment;
    private String mensagemPrefixo;

    public UserDTO(String nome, String celular, String cpf, String email, EstablishmentDTO establishment,
	    String mensagemPrefixo) {
	this.nome = nome;
	this.celular = celular;
	this.cpf = cpf;
	this.email = email;
	this.establishment = establishment;
	this.mensagemPrefixo = mensagemPrefixo;
    }

    public UserDTO() {
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getCelular() {
	return celular;
    }

    public void setCelular(String celular) {
	this.celular = celular;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public EstablishmentDTO getEstablishment() {
	return establishment;
    }

    public void setEstablishment(EstablishmentDTO establishment) {
	this.establishment = establishment;
    }

    public String getMensagemPrefixo() {
	return mensagemPrefixo;
    }

    public void setMensagemPrefixo(String mensagemPrefixo) {
	this.mensagemPrefixo = mensagemPrefixo;
    }

    @Override
    public String toString() {
	return "UserDTO [nome=" + nome + ", celular=" + celular + ", cpf=" + cpf + ", email=" + email
		+ ", establishment=" + establishment + ", mensagemPrefixo=" + mensagemPrefixo + "]";
    }

    public static class EstablishmentDTO {
	private String nome;
	private String endereco;
	private String cnpj;

	public EstablishmentDTO(String nome, String endereco, String cnpj) {
	    this.nome = nome;
	    this.endereco = endereco;
	    this.cnpj = cnpj;
	}

	public EstablishmentDTO() {
	}

	public String getNome() {
	    return nome;
	}

	public void setNome(String nome) {
	    this.nome = nome;
	}

	public String getEndereco() {
	    return endereco;
	}

	public void setEndereco(String endereco) {
	    this.endereco = endereco;
	}

	public String getCnpj() {
	    return cnpj;
	}

	public void setCnpj(String cnpj) {
	    this.cnpj = cnpj;
	}

	@Override
	public String toString() {
	    return "EstablishmentDTO [nome=" + nome + ", endereco=" + endereco + ", cnpj=" + cnpj + "]";
	}

    }

}
