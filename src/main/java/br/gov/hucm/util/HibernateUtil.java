package br.gov.hucm.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.gov.hucm.entidades.Foto;

public class HibernateUtil {

	public static final SessionFactory session = buildSession();

	private static SessionFactory buildSession() {

		try {
			Configuration config = new Configuration();
			List<Class> mappedClasses = new ArrayList<Class>();
			
			mappedClasses.add(Foto.class);
			
			
			for (Class<?> c : mappedClasses) {
			      config = config.addAnnotatedClass(c);
			}
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/escalas");
			config.setProperty("hibernate.connection.username", "root");
			config.setProperty("hibernate.connection.password", "123456");
			config.setProperty("hibernate.current_session_context_class", "thread");
			config.setProperty("hibernate.show_sql", "true");
			
			

			return config.buildSessionFactory();

		} catch (Throwable b) {

			System.out.println("Não deu \n" + b);
			throw new ExceptionInInitializerError();
		}
	}

	public static SessionFactory getSessionFactory() {
		return session;
	}
}