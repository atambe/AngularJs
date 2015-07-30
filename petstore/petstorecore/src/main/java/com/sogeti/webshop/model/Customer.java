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

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Customer
 * 
 * @author nitshah
 */
@XmlRootElement()
@Entity
@NamedQueries({ @NamedQuery(name = Customer.FIND_BY_LOGIN, query = "SELECT c FROM Customer c WHERE c.loginname = :loginname"),
   @NamedQuery(name = Customer.FIND_BY_LOGIN_PASSWORD, query = "SELECT c FROM Customer c WHERE c.loginname = :loginname AND c.password = :password"),
   @NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c") })
public class Customer implements Serializable
{

   public static final String FIND_BY_LOGIN = "Customer.findByLogin";
   public static final String FIND_BY_LOGIN_PASSWORD = "Customer.findByLoginAndPassword";
   public static final String FIND_ALL = "Customer.findAll";
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String loginname;

   private String password;

   @NotNull
   private String firstname;

   @NotNull
   private String lastname;

   @NotNull
   private String mobile;

   @NotNull
   private String email;

   @Embedded
   @Valid
   private Address homeAddress = new Address();

   public Customer()
   {
   }

   /**
    * Instantiates a new customer.
    *
    * @param loginname the loginname
    * @param password the password
    * @param firstname the firstname
    * @param lastname the lastname
    * @param mobile the mobile
    * @param email the email
    * @param address the address
    */
   public Customer(String loginname, String password, String firstname, String lastname, String mobile, String email, Address address)
   {
      this.loginname = loginname;
      this.password = password;
      this.firstname = firstname;
      this.lastname = lastname;
      this.mobile = mobile;
      this.email = email;
      this.homeAddress = address;
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

   /**
    * Gets the firstname.
    *
    * @return the firstname
    */
   public String getFirstname()
   {
      return this.firstname;
   }

   /**
    * Sets the firstname.
    *
    * @param firstname the new firstname
    */
   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   /**
    * Gets the lastname.
    *
    * @return the lastname
    */
   public String getLastname()
   {
      return this.lastname;
   }

   /**
    * Sets the lastname.
    *
    * @param lastname the new lastname
    */
   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   /**
    * Gets the mobile.
    *
    * @return the mobile
    */
   public String getMobile()
   {
      return this.mobile;
   }

   /**
    * Sets the mobile.
    *
    * @param mobile the new mobile
    */
   public void setMobile(String mobile)
   {
      this.mobile = mobile;
   }

   /**
    * Gets the email.
    *
    * @return the email
    */
   public String getEmail()
   {
      return this.email;
   }

   /**
    * Sets the email.
    *
    * @param email the new email
    */
   public void setEmail(String email)
   {
      this.email = email;
   }

   /**
    * Gets the home address.
    *
    * @return the home address
    */
   public Address gethomeAddress()
   {
      return this.homeAddress;
   }

   /**
    * Sets the home address.
    *
    * @param homeAddress the new home address
    */
   public void sethomeAddress(Address homeAddress)
   {
      this.homeAddress = homeAddress;
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
      result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
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
      if (!(obj instanceof Customer))
      {
         return false;
      }
      Customer other = (Customer) obj;
      if (this.email == null)
      {
         if (other.email != null)
         {
            return false;
         }
      }
      else if (!this.email.equals(other.email))
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
      sb.append("Customer");
      sb.append("{id=").append(this.id);
      sb.append(", login='").append(this.loginname).append('\'');
      sb.append(", password='").append(this.password).append('\'');
      sb.append(", firstname='").append(this.firstname).append('\'');
      sb.append(", lastname='").append(this.lastname).append('\'');
      sb.append(", telephone='").append(this.mobile).append('\'');
      sb.append(", email='").append(this.email).append('\'');
      sb.append(", homeaddress=").append(this.homeAddress);
      sb.append('}');
      return sb.toString();
   }

}
