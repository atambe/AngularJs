/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 14, 2014 Sogeti Nederland B.V. All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Sogeti Nederland B.V.            |  No part of this file may be reproduced
 ** Distributed Software Engineering |  or transmitted in any form or by any
 ** Lange Dreef 17                   |  means, electronic or mechanical, for the
 ** 4131 NJ Vianen                   |  purpose, without the express written
 ** The Netherlands                  |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 */
package com.sogeti.webshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.PurchaseOrder;

/** 
 *Impl class for CustomerServiceDao
 *
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
@Stateless
public class CustomerServiceDaoImpl implements CustomerServiceDao
{

   @PersistenceContext
   private EntityManager entityManager;

   /**
    * Find customer.
    *
    * @param id the id
    * @return the customer
    */
   @Override
   public Customer findCustomer(Long id)
   {
      return this.entityManager.find(Customer.class, id);
   }

   /**
    * Does login user exist.
    *
    * @param String loginname
    * @return true, if successful
    */
   @Override
   public boolean doesUserLogin(final String loginname)
   {
      TypedQuery<Customer> typedQuery = this.entityManager.createNamedQuery(Customer.FIND_BY_LOGIN, Customer.class);
      typedQuery.setParameter("loginname", loginname);
      try
      {
         typedQuery.getSingleResult();
         return true;
      }
      catch (NoResultException e)
      {
         return false;
      }
   }

   /**
    * Creates the customer.
    *
    * @param customer the customer
    * @return the customer
    */
   @Override
   public Customer createCustomer(final Customer customer)
   {

      this.entityManager.persist(customer);

      return customer;
   }

   /**
    * Find customer.
    *
    * @param loginname the loginname
    * @param password the password
    * @return the customer
    */
   @Override
   public Customer findCustomer(final String loginname, final String password)
   {

      TypedQuery<Customer> typedQuery = this.entityManager.createNamedQuery(Customer.FIND_BY_LOGIN_PASSWORD, Customer.class);
      typedQuery.setParameter("loginname", loginname);
      typedQuery.setParameter("password", password);

      try
      {
         return typedQuery.getSingleResult();
      }
      catch (NoResultException e)
      {
         return null;
      }
   }

   /**
    * Update customer.
    *
    * @param customer the customer
    */
   @Override
   public void updateCustomer(Customer customer)
   {

      this.entityManager.merge(customer);

   }

   /**
    * Gets the all customer.
    *
    * @return the all customer
    */
   @Override
   public List<Customer> getAllCustomer()
   {
      CriteriaQuery<Customer> criteria = this.entityManager.getCriteriaBuilder().createQuery(Customer.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Customer.class))).getResultList();
   }
   
   /**
    * Get customer order history by customer ID.
    * 
    * @param Long customerId
    * @return List<PurchaseOrder>
    */
   @Override
   public List<PurchaseOrder> getOrderHistoryByCustId(Long customerId)
   {
      TypedQuery<PurchaseOrder> productBytypedCustomerIdQuery = this.entityManager.createNamedQuery(PurchaseOrder.FIND_BY_CUSTOMER_ID, PurchaseOrder.class);
      productBytypedCustomerIdQuery.setParameter("customerId", customerId);
      return productBytypedCustomerIdQuery.getResultList();
   }
   
   

   /**
    * Set the entityManager to the specified value.
    *
    * @param entityManager The entityManager to set.
    */
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

}
