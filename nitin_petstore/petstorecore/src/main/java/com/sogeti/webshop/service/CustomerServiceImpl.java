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
package com.sogeti.webshop.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sogeti.webshop.dao.CustomerServiceDao;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.PurchaseOrder;

/**
 * impl class for CustomerService
 * 
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
@Stateless
public class CustomerServiceImpl implements CustomerService
{

   @Inject
   private CustomerServiceDao customerServiceDao;

   /**
    * Find customer by Id
    *
    * @param id
    * @return Customer Obj
    */
   @Override
   public Customer findCustomer(Long id)
   {
      return this.customerServiceDao.findCustomer(id);
   }

   /**
    * Does login user exist.
    *
    * @param loginname
    * @return boolean
    */
   @Override
   public boolean doesUserLogin(final String loginname)
   {
      return this.customerServiceDao.doesUserLogin(loginname);
   }

   /**
    * Creates the customer.
    *
    * @param customer
    * @return Customer
    */
   @Override
   public Customer createCustomer(final Customer customer)
   {
      return this.customerServiceDao.createCustomer(customer);
   }

   /**
    * Find customer by userId password
    *
    * @param loginname
    * @param password
    * @return
    */
   @Override
   public Customer findCustomer(final String loginname, final String password)
   {
      return this.customerServiceDao.findCustomer(loginname, password);
   }

   /**
    * Update customer.
    *
    * @param customer
    */
   @Override
   public void updateCustomer(Customer customer)
   {
      this.customerServiceDao.updateCustomer(customer);
   }

   /**
    * Gets the all customer Lisr
    *
    * @return List<Customer>
    */
   @Override
   public List<Customer> getAllCustomer()
   {
      return this.customerServiceDao.getAllCustomer();
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
      return this.customerServiceDao.getOrderHistoryByCustId(customerId);
   }

   /**
    * Set the customerServiceDao to the specified value.
    *
    * @param customerServiceDao The customerServiceDao to set.
    */
   public void setCustomerServiceDao(CustomerServiceDao customerServiceDao)
   {
      this.customerServiceDao = customerServiceDao;
   }
 
}
