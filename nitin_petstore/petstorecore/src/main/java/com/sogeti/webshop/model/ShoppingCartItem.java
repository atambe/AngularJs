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
package com.sogeti.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for ShoppingCartItem
 * 
 * @author nitshah
 */
@XmlRootElement()
@Entity
public class ShoppingCartItem implements Serializable
{

   @NotNull
   private Product item;

   @NotNull
   @Min(1)
   private Integer quantity;

   /**
    * Instantiates a new shopping cart item.
    *
    * @param item the item
    * @param quantity the quantity
    */
   public ShoppingCartItem(Product item, Integer quantity)
   {
      this.item = item;
      this.quantity = quantity;
   }

   /**
    * Gets the item.
    *
    * @return the item
    */
   public Product getItem()
   {
      return this.item;
   }

   /**
    * Sets the item.
    *
    * @param item the new item
    */
   public void setItem(Product item)
   {
      this.item = item;
   }

   /**
    * Gets the quantity.
    *
    * @return the quantity
    */
   public Integer getQuantity()
   {
      return this.quantity;
   }

   /**
    * Sets the quantity.
    *
    * @param quantity the new quantity
    */
   public void setQuantity(Integer quantity)
   {
      this.quantity = quantity;
   }

   /**
    * Gets the sub total.
    *
    * @return the sub total
    */
   public BigDecimal getSubTotal()
   {
      return this.item.getUnitCost().multiply(BigDecimal.valueOf(this.quantity));
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
      {
         return true;
      }
      if (o == null || getClass() != o.getClass())
      {
         return false;
      }

      ShoppingCartItem cartItem = (ShoppingCartItem) o;

      if (!this.item.equals(cartItem.item))
      {
         return false;
      }
      if (!this.quantity.equals(cartItem.quantity))
      {
         return false;
      }

      return true;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      int result = this.item.hashCode();
      result = 31 * result + this.quantity.hashCode();
      return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      final StringBuilder sb = new StringBuilder();
      sb.append("CartItem");
      sb.append("{item='").append(this.item).append('\'');
      sb.append(", quantity='").append(this.quantity).append('\'');
      sb.append('}');
      return sb.toString();
   }
}