/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 7, 2014 Sogeti Nederland B.V. All Rights Reserved.
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

import com.sogeti.webshop.dao.OrderServiceDao;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.PurchaseOrder;
import com.sogeti.webshop.model.ShoppingCartItem;

/**
 * Impl class for OrderService
 * 
 * @author nitshah
 */
@Stateless
public class OrderServiceImpl implements OrderService
{

   @Inject
   private OrderServiceDao orderServiceDao;

   /**
    * Method use to create Order for customer and also apply business logic if any.
    * 
    * @param Customer Object
    * @param List of ShoppingCartItem
    * @return PurchaseOrder Object
    */
   @Override
   public PurchaseOrder createOrder(Customer customer, List<ShoppingCartItem> cartItems)
   {
      return this.orderServiceDao.createOrder(customer, cartItems);
   }

   /**
    * @param orderServiceDao the orderServiceDao to set
    */
   public void setOrderServiceDao(OrderServiceDao orderServiceDao)
   {
      this.orderServiceDao = orderServiceDao;
   }
}
