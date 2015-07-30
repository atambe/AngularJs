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
package com.sogeti.webshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.OrderLine;
import com.sogeti.webshop.model.PurchaseOrder;
import com.sogeti.webshop.model.ShoppingCartItem;

/**
 * Impl class for OrderServiceDao
 *
 * @author nitshah
 */
@Stateless
public class OrderServiceDaoImpl implements OrderServiceDao
{

   @PersistenceContext
   private EntityManager entityManager;

   /**
    * Method use to create Order for customer.
    *
    * @param Customer Object
    * @param List of ShoppingCartItem
    * @return PurchaseOrder Object
    */
   @Override
   public PurchaseOrder createOrder(Customer customer, List<ShoppingCartItem> cartItems)
   {
      if (cartItems.isEmpty())
      {
         throw new ValidationException("Shopping cart is empty");
      }

      PurchaseOrder purchaseOrder = new PurchaseOrder(this.entityManager.merge(customer), customer.gethomeAddress());
      List<OrderLine> orderLines = new ArrayList<OrderLine>();
      for (ShoppingCartItem cartItem : cartItems)
      {
         orderLines.add(new OrderLine(cartItem.getQuantity(), this.entityManager.merge(cartItem.getItem())));
      }

      purchaseOrder.setOrderLines(orderLines);
      this.entityManager.persist(purchaseOrder);

      return purchaseOrder;
   }

   /**
    * @param entityManager the entityManager to set
    */
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

}
