package newsreader;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtilities {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Configuration configuration;

	private HibernateUtilities() {}

	static {

		configuration = new Configuration().configure().addAnnotatedClass(Users.class).addAnnotatedClass(Subscription.class).addAnnotatedClass(NewspaperLookup.class);

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();	
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	}

	public static SessionFactory getSessionFactory() {

		if(sessionFactory == null)
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;

	}
}
