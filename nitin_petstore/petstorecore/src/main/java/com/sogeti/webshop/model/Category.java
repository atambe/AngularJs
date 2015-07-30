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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Category
 *
 * @author nitshah
 */
@XmlRootElement()
@Entity
@NamedQueries({ @NamedQuery(name = Category.FIND_BY_NAME, query = "SELECT c FROM Category c WHERE c.name = :categoryname"), @NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c") })
public class Category implements Serializable
{

   public static final String FIND_BY_NAME = "Category.findByName";
   public static final String FIND_ALL = "Category.findAll";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 30)
   @NotNull
   @Size(min = 1, max = 30)
   private String name;

//   @Column(nullable = false)
//   @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//   @OrderBy("name ASC")
//   private List<Product> products;

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

//   /**
//    * @return the products
//    */
//   public List<Product> getProducts()
//   {
//      return this.products;
//   }
//
//   /**
//    * @param products the products to set
//    */
//   public void setProducts(List<Product> products)
//   {
//      this.products = products;
//   }

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
      result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
      //result = prime * result + ((this.products == null) ? 0 : this.products.hashCode());
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
      Category other = (Category) obj;
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
//      if (this.products == null)
//      {
//         if (other.products != null)
//         {
//            return false;
//         }
//      }
//      else if (!this.products.equals(other.products))
//      {
//         return false;
//      }
      return true;
   }

}
