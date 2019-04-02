package br.com.centralservicos.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.centralservicos.domain.Usuario;
import br.com.centralservicos.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario autenticar(String usuario, String senha) {
	
	
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	try {
		
		Criteria consulta = sessao.createCriteria(Usuario.class);
		
		//Pegando o nome de usuário da pessoa. 
		consulta.createAlias("pessoa", "p");
		consulta.add(Restrictions.eq("p.nomeusuario", usuario));
		
		//criando e convertendo a senha para md5
		SimpleHash hash = new SimpleHash("md5", senha);
		consulta.add(Restrictions.eq("senha", hash.toHex()));
		
		Usuario resultado = (Usuario) consulta.uniqueResult();
		
		return resultado;
		
	} catch (RuntimeException e) {
		throw e;
	}finally {
		sessao.close();
	}
	}
	
	
	
//	public Usuario autenticar(String usuario, String senha) {
//		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
//		try {
//			
//			Criteria consulta = sessao.createCriteria(Usuario.class);
//			
//			consulta.createAlias("pessoa", "p");
//			consulta.add(Restrictions.eq("p.cpf", cpf));
//
//			SimpleHash hash = new SimpleHash("md5", senha);
//			consulta.add(Restrictions.eq("senha", hash.toHex()));
//			
//			Usuario resultado = (Usuario) consulta.uniqueResult();
//
//			return resultado;
//
//		} catch (RuntimeException erro) {
//			throw erro;
//		} finally {
//			sessao.close();
//		}
//
//	}
}


/*Consulta CPF existente da pessoa
 * consulta.createAlias("pessoa", "p");
	consulta.add(Restrictions.eq("p.cpf", cpf));
 * 
 * Consulta Senha existente criptografada.
 * SimpleHash hash = new SimpleHash("md5", senha);
	consulta.add(Restrictions.eq("senha", hash.toHex()));
 * 
 * Retornando um unico resultado da união das duas consultas. 
 * 
 * Usuario resultado = (Usuario) consulta.uniqueResult();
 * */

