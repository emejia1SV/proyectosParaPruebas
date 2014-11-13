/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 22, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import de.laliluna.examples.Agregadores;

/**
 * Este clase garantiza que solo est� instanciado una ves el SessionFactory y
 * que la configuraci�n se realiza hilo seguro como singleton. En realidad, s�lo
 * envuelve la Hibernate SessionFactory. Usted es libre de utilizar cualquier
 * tipo de JTA o Thread transactionFactories.
 * 
 * @author Edwin Mejia - Avantia Consultores
 */
public class SessionFactoryUtil {

	/** The single instance of hibernate SessionFactory */
	private static org.hibernate.SessionFactory sessionFactory;
	private static org.hibernate.service.ServiceRegistry serviceRegistry;

	/**
	 * Desabilita el contructor y garntiza una sola instancia
	 */
	private SessionFactoryUtil() {
	}

	static {
		/*Configuration configuration = new Configuration();
		configuration.addPackage("de.laliluna.examples");
		configuration.addAnnotatedClass(Agregadores.class);
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	/**
	 * Abre una sesi�n y no se unir� a un contexto de sesi�n
	 * 
	 * @return the session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Devuelve una sesi�n desde el contexto de sesi�n. Si no hay sesiones en el
	 * contexto que se abre una sesi�n, la almacena en el contexto y lo
	 * devuelve. Esta f�brica est� destinado a ser utilizado con un
	 * hibernate.cfg.xml incluyendo la siguiente propiedad <property
	 * name="current_session_context_class">thread</property> Esto devolver�a la
	 * sesi�n actual abierta o si este no existe, se crear� una nueva sesi�n
	 * 
	 * @return the session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * closes the session factory
	 */
	public static void close() {
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;

	}
}