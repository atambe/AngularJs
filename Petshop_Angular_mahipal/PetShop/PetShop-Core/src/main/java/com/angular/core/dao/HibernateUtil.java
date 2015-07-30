package com.angular.core.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * This is a dao class that handle all utility operations like insert update delete
 * @author mahijain
 *
 */
@SuppressWarnings("deprecation")
public class HibernateUtil 
{
	private static final  SessionFactory FACTORY = new HibernateUtil().buildSessionFactory();
	
	/**
	 * This method will give session factory for creating session. 
	 * @return SessionFactory
	 */
	private  SessionFactory buildSessionFactory() 
	{
		try
		{			
			return new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} 
		catch (Exception ex) 
		{
			
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() 
	{
		return FACTORY;
	}

	/**
	 * This method will close session.
	 */
	public  void shutdown() 
	{
		getSessionFactory().close();
	}

}
