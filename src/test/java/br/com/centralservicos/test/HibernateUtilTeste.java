package br.com.centralservicos.test;

import org.hibernate.Session;
import org.junit.Test;

import br.com.centralservicos.util.HibernateUtil;

public class HibernateUtilTeste {

	@Test
	public void conectar() {
		
		try {
			Session session = HibernateUtil.getFabricaDeSessoes().openSession();
			session.close();
			HibernateUtil.getFabricaDeSessoes().close();
			System.out.println("Conectado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("O erro foi: "+ e.getMessage());
		}
	}
}

/*Classe responsável por criar e altearar as classes que estão no arquivo hibernate.cfg.xml.
 * 
 */

