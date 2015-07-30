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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model Class for Product
 * 
 * @author nitshah
 */
@XmlRootElement()
@Entity
@NamedQueries({ @NamedQuery(name = Product.SEARCH, query = "SELECT p FROM Product p WHERE UPPER(p.name) LIKE :keyword ORDER BY p.category.name , p.name"),
      @NamedQuery(name = Product.FIND_BY_CATEGORY_ID, query = "SELECT p FROM Product p WHERE p.category.id = :categoryId"), @NamedQuery(name = Product.FIND_ALL, query = "SELECT p FROM Product p") })
public class Product implements Serializable
{

   public static final String SEARCH = "Product.search";
   public static final String FIND_ALL = "Product.findAll";
   public static final String FIND_BY_CATEGORY_ID = "Product.findByCategoryId";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 30)
   @NotNull
   @Size(min = 1, max = 30)
   private String name;

   @Column(nullable = false)
   private BigDecimal unitCost;

   @Column(nullable = false)
   private String description;

   @ManyToOne
   @JoinColumn(name = "Category_ID", nullable = false)
   private Category category;

   /**
    * @return the id
    */
   public Long getId()
   {
      return this.id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id)
   {
      this.id = id;
   }

   /**
    * @return the name
    */
   public String getName()
   {
      return this.name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      this.name = name;
   }

   /**
    * @return the unitCost
    */
   public BigDecimal getUnitCost()
   {
      return this.unitCost;
   }

   /**
    * @param unitCost the unitCost to set
    */
   public void setUnitCost(BigDecimal unitCost)
   {
      this.unitCost = unitCost;
   }

   /**
    * @return the description
    */
   public String getDescription()
   {
      return this.description;
   }

   /**
    * @param description the description to set
    */
   public void setDescription(String description)
   {
      this.description = description;
   }

   /**
    * @return the category
    */
   public Category getCategory()
   {
      return this.category;
   }

   /**
    * @param category the category to set
    */
   public void setCategory(Category category)
   {
      this.category = category;
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
      result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
      result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
      result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
      result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
      result = prime * result + ((this.unitCost == null) ? 0 : this.unitCost.hashCode());
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
      if (getClass() != obj.getClass())
      {
         return false;
      }
      Product other = (Product) obj;
      if (this.category == null)
      {
         if (other.category != null)
         {
            return false;
         }
      }
      else if (!this.category.equals(other.category))
      {
         return false;
      }
      if (this.description == null)
      {
         if (other.description != null)
         {
            return false;
         }
      }
      else if (!this.description.equals(other.description))
      {
         return false;
      }
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
      if (this.name == null)
      {
         if (other.name != null)
         {
            return false;
         }
      }
      else if (!this.name.equals(other.name))
      {
         return false;
      }
      if (this.unitCost == null)
      {
         if (other.unitCost != null)
         {
            return false;
         }
      }
      else if (!this.unitCost.equals(other.unitCost))
      {
         return false;
      }
      return true;
   }
}
