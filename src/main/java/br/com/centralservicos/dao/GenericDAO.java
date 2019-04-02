package br.com.centralservicos.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.centralservicos.util.HibernateUtil;

public class GenericDAO<Entidade> {

	/**
	 * Recebe a classe de forma genérica
	 */
	@SuppressWarnings("unused")
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction trasacao = null;
		

		try {
			trasacao = sessao.beginTransaction();
			sessao.save(entidade);

			trasacao.commit();

		} catch (Exception erro) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction trasacao = null;

		try {
			trasacao = sessao.beginTransaction();
			sessao.merge(entidade);

			trasacao.commit();

		} catch (Exception erro) {
			if (trasacao != null) {
				trasacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Retorna uma lista genérica da classe passada por parametro
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consuta = session.createCriteria(classe);
			List<Entidade> resultado = consuta.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			session.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listarOrdenacao(String ordenacao){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria  consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(ordenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}finally {
			sessao.close();
		}
		
		/*Método responsável por listar geralmente os combos de forma ordena. Pode ser 
		 * utilizar nos metodos de cadastrar, novo e editar.* 
		 */
	}
	

	@SuppressWarnings("unchecked")
	public Entidade buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = session.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			session.close();
		}

	}

	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;

		} finally {
			sessao.close();
		}
	}
}

/*
 * Essa classe vai servir para reaproveitamento de código. Ou seja não será mas
 * necessário repetir os quatro métodos para para cada classe DAO que cria.
 * Simplesmente será herdado. Não será mas necessário adicionar nenhuma
 * informação.
 * 
 * ex: No momento da criação da classe basta extender o GenericDAO e informar o
 * nome da classe de dominio
 * 
 * public class EstadoDAO extends GenericDAO<Estado> {}
 * 
 */

/*
 * Esse padrão abaixo e exigido na documentação na classe genérica (no
 * construtor) para para listar os itens.
 * 
 * @SuppressWarnings("unused") private Class<Entidade> classe;
 * 
 * @SuppressWarnings("unchecked") public GenericDAO() { this.classe =
 * (Class<Entidade>) ((ParameterizedType)getClass().
 * getGenericSuperclass()).getActualTypeArguments()[0];}
 * 
 */

/*
 * MERGE
 * 
 * é um método que pode tando salvar quando atualizar. Se já existe ele atualiza
 * (alterar um dado) se não salva outro
 */
