package br.com.centralservicos.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	
	
	public static Connection getConexao(){
		Session sessao = fabricaDeSessoes.openSession();
		
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			
			@Override
			public Connection execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				return conn;
			}
		});
		
		return conexao;
	}

	
	
	
	
	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();

			SessionFactory fabrica = configuracao.buildSessionFactory(registro);

			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A conexão não foi criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}



//private static SessionFactory criarFabricaDeSessoes() {
//	
//	try {
//		Configuration configuracao = new Configuration().configure();
//		
//		ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
//		
//		SessionFactory factory = configuracao.buildSessionFactory(registro);
//				
//		return factory;
//		
//	} catch (Throwable ex) {
//		ex.printStackTrace();
//		System.err.println("A conexão não foi criada." + ex);
//		throw new ExceptionInInitializerError(ex);
//	}
//}
//
//private static SessionFactory fabicarcaDeSessoes = criarFabricaDeSessoes();
//
////Responsável por obter as  seções nas  classes que  serão chamadas e não mostrar como foi obtida.
//public static SessionFactory getFabricaDeSessoes() {
//	return fabicarcaDeSessoes;
//}
