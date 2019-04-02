package br.com.centralservicos.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Programa extends GenericDomain{

	@NotEmpty(message="Informe o nome do programa")
	private String programa;
	
	@ManyToOne//um secretario pode tomar conta de muitos  programas
	@JoinColumn(nullable=false)
	private Pessoa secretario;
	
	private String ramal;

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Pessoa getSecretario() {
		return secretario;
	}

	public void setSecretario(Pessoa secretario) {
		this.secretario = secretario;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	
}
