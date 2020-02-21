package br.com.sms.login.dto;

public class RegisterDTO {

    private String nome;
    private String celular;
    private String cpf;
    private String email;
    private String password;
    private EstablishmentDTO establishment;

    public RegisterDTO(String nome, String celular, String cpf, String email, String password,
	    EstablishmentDTO establishment) {
	this.nome = nome;
	this.celular = celular;
	this.cpf = cpf;
	this.email = email;
	this.password = password;
	this.establishment = establishment;
    }

    public RegisterDTO() {
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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "RegisterDTO [nome=" + nome + ", celular=" + celular + ", cpf=" + cpf + ", email=" + email
		+ ", password=" + password + ", establishment=" + establishment + "]";
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
