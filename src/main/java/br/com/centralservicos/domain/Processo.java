package br.com.centralservicos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQuery(name="Processo.findAll", query="FROM Processo")
public class Processo extends GenericDomain{

	@NotEmpty(message="Informe um Processo")
	private String processo;
	
	@NotEmpty(message="Informe uma Descrição")
	@Column(length=3000, nullable=false)
	private String descricao;
	
	private String referencia;
	
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
	
}
