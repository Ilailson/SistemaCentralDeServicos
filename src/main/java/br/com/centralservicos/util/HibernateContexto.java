package br.com.centralservicos.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}

/*
 * Essa classe será responsável por iniciar o Hibernate junto com o servidor. 
 * Vai demorar para inicializar  na primeira vez.
 * 
 * Objetivo. Fazer com que a navegação das páginas fiquem mas rápida. 
 * 
 * Configuração WEB.XML
 */

/*Configuração POM
 * <dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
		</dependency>
 * 
 */

/*Configuração web.xml
 * <listener>
		<listener-class>br.com.financeiro.util.HibernateContexto</listener-class>
	</listener>
 * */

