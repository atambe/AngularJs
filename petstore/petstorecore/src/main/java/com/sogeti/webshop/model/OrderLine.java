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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model Class for OrderLine
 *
 * @author nitshah
 */
@XmlRootElement()
@Entity
public class OrderLine implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   private Integer quantity;

   @ManyToOne
   @JoinColumn(name = "Product_ID")
   private Product item;

   public OrderLine()
   {
   }

   /**
    * Instantiates a new order line.
    *
    * @param quantity the quantity
    * @param item the item
    */
   public OrderLine(Integer quantity, Product item)
   {
      this.quantity = quantity;
      this.item = item;

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
    * Gets the product.
    *
    * @return the product
    */
   public Product getProduct()
   {
      return this.item;
   }

   /**
    * Sets the product.
    *
    * @param item the new product
    */
   public void setProduct(Product item)
   {
      this.item = item;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.item == null) ? 0 : this.item.hashCode());
      result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
      return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null)
      {
         return false;
      }
      if (!(obj instanceof OrderLine))
      {
         return false;
      }
      OrderLine other = (OrderLine) obj;
      if (this.item == null)
      {
         if (other.item != null)
         {
            return false;
         }
      }
      else if (!this.item.equals(other.item))
      {
         return false;
      }
      if (this.quantity == null)
      {
         if (other.quantity != null)
         {
            return false;
         }
      }
      else if (!this.quantity.equals(other.quantity))
      {
         return false;
      }
      return true;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      final StringBuilder sb = new StringBuilder();
      sb.append("OrderLine");
      sb.append("{id=").append(this.id);
      sb.append(", quantity=").append(this.quantity);
      sb.append(", item=").append(this.item);
      sb.append('}');
      return sb.toString();
   }
}
