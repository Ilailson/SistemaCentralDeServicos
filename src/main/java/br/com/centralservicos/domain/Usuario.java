package br.com.centralservicos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Usuario extends GenericDomain {

	@Column(length = 32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@OneToOne // uma pessoa pode ter apenas um usuário
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;

		if (tipo == 'A') {
			tipoFormatado = "Administrador";

		} else if (tipo == 'B') {
			tipoFormatado = "Balconista";
		} else if (tipo == 'G') {
			tipoFormatado = "Gerente";
		}

		return tipoFormatado;
		
		/*
		 * Esse método será responsável por fazer com que um tipo bit se transforme em
		 * uma string na visualização da tela
		 * 
		 * @Transient: essa anotação informar que esse método não pertence ao banco de
		 * dados. Toda vez que precisar adicionar um metodo em uma classe mapeada para
		 * banco que não queira que seja adicionada basta adicionar essa anotação.
		 *
		 *Adicionado a variavel na exibição.
		 * <p:column headerText="Tipo" sortBy="#{usuario.tipoFormatado}">
						<h:outputText value="#{usuario.tipoFormatado}" />
					</p:column>
		 */
	}

	@Override
	public String toString() {
		return "Usuario [senha=" + senha + ", tipo=" + tipo + ", pessoa=" + pessoa + "]";
	}

	
}
