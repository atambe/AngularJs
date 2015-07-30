package com.angular.core.impservice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.angular.core.dao.HibernateUtil;
import com.angular.core.entity.CustomerRegisterBeans;

@Service
public class CommonUtility {

	/**
	 * This method is used to call user validation from data base
	 * @param email
	 * @param Pass
	 * @return
	 */
	public String authenticateUser(String email,String Pass)
	{
		String encryptedPassword = null;
		String status;
		encryptedPassword = CustomerRegisterService.getPassword(Pass);
		status = checkUserExists(email,encryptedPassword);	
		return status;
	}
	
	/**
	 * This method check if user exists or not
	 * @param email
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String checkUserExists(String email,String password)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<CustomerRegisterBeans> customer = null;	
		try 
		{
			tx = session.beginTransaction();
			Criteria query = session.createCriteria(CustomerRegisterBeans.class);
			customer = query.list();
			tx.commit();
		} 
		catch (HibernateException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally 
		{
			session.close();
		}

		if(customer != null)
		{
			for (CustomerRegisterBeans customerRegisterBeans : customer)
	        {    
				if( customerRegisterBeans.getEmail().equals(email) && customerRegisterBeans.getPassword().equals(password))
	    		{
					System.out.println("!!!!!!!!!!!!!!!!!!!!! inside if loop !!!!!!!!!!!!!!!!!!!!!");
					return customerRegisterBeans.getUserName();
	    		}
			}			
			return "failed";
		}
		else
		{
			return "error";
		}
	}
	
}
