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
package com.sogeti.webshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Category
 *
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
@XmlRootElement()
@Entity
@NamedQueries({ @NamedQuery(name = Admin.FIND_BY_LOGIN_PASSWORD, query = "SELECT c FROM Admin c WHERE c.loginname = :loginname AND c.password = :password") })
public class Admin implements Serializable
{

   public static final String FIND_BY_LOGIN_PASSWORD = "Admin.findByLoginAndPassword";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String loginname;

   private String password;

   /**
    * Instantiates a new admin.
    */
   public Admin()
   {

   }

   /**
    * Instantiates a new admin.
    *
    * @param loginname the loginname
    * @param password the password
    */
   public Admin(String loginname, String password)
   {
      super();
      this.loginname = loginname;
      this.password = password;
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
    * Sets the id.
    *
    * @param id the new id
    */
   public void setId(Long id)
   {
      this.id = id;
   }

   /**
    * Gets the loginname.
    *
    * @return the loginname
    */
   public String getLoginname()
   {
      return this.loginname;
   }

   /**
    * Sets the loginname.
    *
    * @param loginname the new loginname
    */
   public void setLoginname(String loginname)
   {
      this.loginname = loginname;
   }

   /**
    * Gets the password.
    *
    * @return the password
    */
   public String getPassword()
   {
      return this.password;
   }

   /**
    * Sets the password.
    *
    * @param password the new password
    */
   public void setPassword(String password)
   {
      this.password = password;
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
      result = prime * result + ((this.loginname == null) ? 0 : this.loginname.hashCode());
      result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
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
      if (!(obj instanceof Admin))
      {
         return false;
      }
      Admin other = (Admin) obj;
      if (this.loginname == null)
      {
         if (other.loginname != null)
         {
            return false;
         }
      }
      else if (!this.loginname.equals(other.loginname))
      {
         return false;
      }
      if (this.password == null)
      {
         if (other.password != null)
         {
            return false;
         }
      }
      else if (!this.password.equals(other.password))
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
      return "Admin [loginname=" + this.loginname + ", password=" + this.password + "]";
   }

}
