package br.com.centralservicos.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Responsável por gerar as chaves primárias automaticamente
 * para todas as outras classes. Basta colocar a anotação abaixo <p>
 * MappedSuperclass
 * 
 * @author Ilailson Rocha
 *
 */
@MappedSuperclass
public class GenericDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}

/*Essa classe será responsável por gerar as chaves primárias automaticamente
 * para todas as outras classes. Basta colocar a anotação abaixo
 * @MappedSuperclass
 */

/*equals & hashCode
 * quando se trabalha com chave estrangeira tem que adicionar esses métodos.
 *Mas como esse é uma classe genérica basta adicionar nela que  todas 
 *as outras herdarão. Que são os combobox, seleção de menu etc...
 * */
 
