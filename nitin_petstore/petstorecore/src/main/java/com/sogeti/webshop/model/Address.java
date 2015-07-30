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

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Address
 *
 * @author nitshah
 */
@XmlRootElement()
@Entity
@Embeddable
public class Address implements Serializable
{

   @NotNull
   private String street;

   @NotNull
   private String city;

   @NotNull
   private String country;

   @NotNull
   private String zipcode;

   public Address()
   {
   }

   /**
    * Instantiates a new address.
    *
    * @param street the street
    * @param city the city
    * @param zipcode the zipcode
    * @param country the country
    */
   public Address(String street, String city, String zipcode, String country)
   {
      this.street = street;
      this.city = city;
      this.zipcode = zipcode;
      this.country = country;
   }

   /**
    * Gets the street.
    *
    * @return the street
    */
   public String getStreet()
   {
      return this.street;
   }

   /**
    * Sets the street.
    *
    * @param street the new street
    */
   public void setStreet(String street)
   {
      this.street = street;
   }

   /**
    * Gets the city.
    *
    * @return the city
    */
   public String getCity()
   {
      return this.city;
   }

   /**
    * Sets the city.
    *
    * @param city the new city
    */
   public void setCity(String city)
   {
      this.city = city;
   }

   /**
    * Gets the zipcode.
    *
    * @return the zipcode
    */
   public String getZipcode()
   {
      return this.zipcode;
   }

   /**
    * Sets the zipcode.
    *
    * @param zipcode the new zipcode
    */
   public void setZipcode(String zipcode)
   {
      this.zipcode = zipcode;
   }

   /**
    * Gets the country.
    *
    * @return the country
    */
   public String getCountry()
   {
      return this.country;
   }

   /**
    * Sets the country.
    *
    * @param country the new country
    */
   public void setCountry(String country)
   {
      this.country = country;
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
      result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
      result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
      result = prime * result + ((this.street == null) ? 0 : this.street.hashCode());
      result = prime * result + ((this.zipcode == null) ? 0 : this.zipcode.hashCode());
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
      if (!(obj instanceof Address))
      {
         return false;
      }
      Address other = (Address) obj;
      if (this.city == null)
      {
         if (other.city != null)
         {
            return false;
         }
      }
      else if (!this.city.equals(other.city))
      {
         return false;
      }
      if (this.country == null)
      {
         if (other.country != null)
         {
            return false;
         }
      }
      else if (!this.country.equals(other.country))
      {
         return false;
      }
      if (this.street == null)
      {
         if (other.street != null)
         {
            return false;
         }
      }
      else if (!this.street.equals(other.street))
      {
         return false;
      }
      if (this.zipcode == null)
      {
         if (other.zipcode != null)
         {
            return false;
         }
      }
      else if (!this.zipcode.equals(other.zipcode))
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
      sb.append("Address");
      sb.append("{street='").append(this.street).append('\'');
      sb.append(", city='").append(this.city).append('\'');
      sb.append(", zipcode='").append(this.zipcode).append('\'');
      sb.append(", country='").append(this.country).append('\'');
      sb.append('}');
      return sb.toString();
   }
}
