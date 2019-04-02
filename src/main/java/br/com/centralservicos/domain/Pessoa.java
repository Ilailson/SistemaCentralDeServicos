package br.com.centralservicos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pessoa extends GenericDomain{

	@Column(length=50, nullable=false)
	private String nome;
	
	@NotEmpty(message="Informe o nome de usuário")
	private String nomeusuario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeusuario() {
		return nomeusuario;
	}

	public void setNomeusuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", nomeusuario=" + nomeusuario + "]";
	}
	
	
}
