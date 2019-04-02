package br.com.centralservicos.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Secao extends GenericDomain{

	@NotNull(message="Favor informe um nome")
	@NotEmpty(message="Favor informe um nome")
	@Size(min=5, max=50, message="Nome tem que ter entre 5 e 50 letras")
	private String nome;
	
	@NotNull(message="Favor informe a Sigla")
	@NotEmpty(message="Favor informe a Sigla")
	@Size(min=3, max=30,  message="Sigla  tem que entre 3 e 30 letras")
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	
}
