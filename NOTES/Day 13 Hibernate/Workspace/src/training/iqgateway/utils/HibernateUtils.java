package training.iqgateway.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import training.iqgateway.entities.OffenceTypesEO;

public class HibernateUtils {
	
	private static final SessionFactory sessionFactory;
	
	static{
		Configuration cfg = new Configuration().addClass(OffenceTypesEO.class);
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
