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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for PurchaseOrder
 * 
 * @author nitshah
 */
@XmlRootElement()
@Entity
@NamedQueries({ @NamedQuery(name = PurchaseOrder.FIND_ALL, query = "SELECT o FROM PurchaseOrder o"),
   @NamedQuery(name = PurchaseOrder.FIND_BY_CUSTOMER_ID, query = "SELECT o FROM PurchaseOrder o WHERE o.customer.id = :customerId") })
public class PurchaseOrder implements Serializable
{
   public static final String FIND_BY_CUSTOMER_ID = "PurchaseOrder.findByCustomerId";
   public static final String FIND_ALL = "PurchaseOrder.findAll";
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Temporal(TemporalType.DATE)
   private Date orderDate;

   @Embedded
   private Address deliveryAddress;

   @ManyToOne
   @JoinColumn(name = "Customer_Id")
   private Customer customer;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "Order_Id")
   private List<OrderLine> orderLines;

   public PurchaseOrder()
   {
   }

   /**
    * Instantiates a new purchase order.
    *
    * @param customer the customer
    * @param deliveryAddress the delivery address
    */
   public PurchaseOrder(Customer customer, Address deliveryAddress)
   {
      this.customer = customer;
      this.deliveryAddress = deliveryAddress;

   }

   /**
    * Sets the default data.
    */
   @PrePersist
   private void setDefaultData()
   {
      this.orderDate = new Date();
   }

   /**
    * Gets the total.
    *
    * @return the total
    */
   public BigDecimal getTotal()
   {
      if (this.orderLines == null || this.orderLines.isEmpty())
      {
         return BigDecimal.ZERO;
      }

      BigDecimal total = BigDecimal.ZERO;

      for (OrderLine orderLine : this.orderLines)
      {
         total = total.add(orderLine.getSubTotal());
      }

      return total;
   }

   /**
    * Gets the id.
    *
    * @return the id
    */
   public Long getId()
   {
      return this.id;
   }

   /**
    * Gets the order date.
    *
    * @return the order date
    */
   public Date getOrderDate()
   {
      return this.orderDate;
   }

   /**
    * Gets the delivery address.
    *
    * @return the delivery address
    */
   public Address getDeliveryAddress()
   {
      return this.deliveryAddress;
   }

   /**
    * Sets the delivery address.
    *
    * @param deliveryAddress the new delivery address
    */
   public void setDeliveryAddress(Address deliveryAddress)
   {
      this.deliveryAddress = deliveryAddress;
   }

   /**
    * Gets the customer.
    *
    * @return the customer
    */
   public Customer getCustomer()
   {
      return this.customer;
   }

   /**
    * Sets the customer.
    *
    * @param customer the new customer
    */
   public void setCustomer(Customer customer)
   {
      this.customer = customer;
   }

   /**
    * Gets the order lines.
    *
    * @return the order lines
    */
   public List<OrderLine> getOrderLines()
   {
      return this.orderLines;
   }

   /**
    * Sets the order lines.
    *
    * @param orderLines the new order lines
    */
   public void setOrderLines(List<OrderLine> orderLines)
   {
      this.orderLines = orderLines;
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
      result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
      result = prime * result + ((this.orderDate == null) ? 0 : this.orderDate.hashCode());
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
      if (!(obj instanceof PurchaseOrder))
      {
         return false;
      }
      PurchaseOrder other = (PurchaseOrder) obj;
      if (this.id == null)
      {
         if (other.id != null)
         {
            return false;
         }
      }
      else if (!this.id.equals(other.id))
      {
         return false;
      }
      if (this.orderDate == null)
      {
         if (other.orderDate != null)
         {
            return false;
         }
      }
      else if (!this.orderDate.equals(other.orderDate))
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
      sb.append("PurchaseOrder");
      sb.append("{id=").append(this.id);
      sb.append(", orderDate=").append(this.orderDate);
      sb.append(", customer=").append(this.customer);
      sb.append(", orderLines=").append(this.orderLines);
      sb.append(", deliveryAddress=").append(this.deliveryAddress);
      sb.append('}');
      return sb.toString();
   }

}
