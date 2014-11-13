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
 * Este clase garantiza que solo está instanciado una ves el SessionFactory y
 * que la configuración se realiza hilo seguro como singleton. En realidad, sólo
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
	 * Abre una sesión y no se unirá a un contexto de sesión
	 * 
	 * @return the session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Devuelve una sesión desde el contexto de sesión. Si no hay sesiones en el
	 * contexto que se abre una sesión, la almacena en el contexto y lo
	 * devuelve. Esta fábrica está destinado a ser utilizado con un
	 * hibernate.cfg.xml incluyendo la siguiente propiedad <property
	 * name="current_session_context_class">thread</property> Esto devolvería la
	 * sesión actual abierta o si este no existe, se creará una nueva sesión
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