package br.com.centralservicos.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Responsável por criar os atributos no banco de dados da tabela chefia
 * @author Ilailson Rocha
 *
 *@see GenericDomain
 */
@Entity
public class Chefia extends GenericDomain{

	
	
	@ManyToOne//uma seção  pode ter várias chefias
	@JoinColumn(nullable=false)
	private Secao secao;
	
	
	@ManyToOne//uma pessoa  pode esta em muitas chefias 
	@JoinColumn(nullable=false)
	private Pessoa pessoa;
	
	
	@ManyToOne//uma pessoa  pode esta em muitas chefias 
	@JoinColumn(nullable=false)
	private Pessoa substituto;

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getSubstituto() {
		return substituto;
	}

	public void setSubstituto(Pessoa substituto) {
		this.substituto = substituto;
	}

	
}
