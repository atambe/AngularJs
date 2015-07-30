package com.angular.core.impservice;


import java.security.MessageDigest;

import org.hibernate.*;
import org.springframework.stereotype.Service;

import com.angular.core.entity.CustomerRegisterBeans;
import com.angular.core.iservice.ICustomerRegisterService;
import com.angular.core.dao.HibernateUtil;

@Service
public class CustomerRegisterService implements ICustomerRegisterService {
	
	private String password;
	
	/**
	 * This method process the customer registration activity
	 */
	public void processRegisteration(String userName, String email, String userPassword)
	{
		// getting encrypted password for the customer
		password = getPassword(userPassword);
		// Store customer information
		insertLoginDetails(userName,email);		
	}
	
	public void insertLoginDetails(String userName, String email)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();   
		Transaction t=session.beginTransaction();  
		CustomerRegisterBeans registerBeans = new CustomerRegisterBeans();
		registerBeans.setUserName(userName);
		registerBeans.setPassword(password);
		registerBeans.setEmail(email);
		registerBeans.setType("Customer");
		session.persist(registerBeans);     
		t.commit();  
		session.close();  
	}
	
	/**
	 * This method is used to encrypt password
	 * @param data
	 * @return
	 */
	public static String getPassword(String data) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(data.getBytes("UTF-8"));
            byte[] digestBytes = messageDigest.digest();
            String hex = null;
            for (int i = 0; i < digestBytes.length; i++) {
                //Convert it to positive integer and then to Hex String
                hex = Integer.toHexString(0xFF & digestBytes[i]);
                if (hex.length() < 2) 
                {
                    sb.append("0");
                }
                    sb.append(hex);
                }
            }
        catch (Exception ex) {
        	 return new String(sb);
            }
        return new String(sb);
    }

	/**
	 * This method is used to update admin password in data base
	 * @param proId
	 */
	public String updatePassword(CustomerRegisterBeans beans, String password)
	{
			Session session = HibernateUtil.getSessionFactory().openSession();
			try 
			{
				session.beginTransaction();		
				deleteUser(beans.getUserId());				
				beans.setPassword(getPassword(password));
				session.save(beans);
				session.getTransaction().commit();
				return "UPDATE";
			} 
			catch (HibernateException e)
			{
				session.getTransaction().rollback();
				return "FAIL";
			}
			finally 
			{
				session.close();
			}	
		
		}
	
	public void deleteUser(int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try 
		{
			session.beginTransaction();
			CustomerRegisterBeans proBean = (CustomerRegisterBeans) session.get(CustomerRegisterBeans.class, id);
			session.delete(proBean);
			session.getTransaction().commit();
		} 
		catch (HibernateException e)
		{
			session.getTransaction().rollback();
		}
		finally 
		{
			session.close();
		}	
	
	}
}
