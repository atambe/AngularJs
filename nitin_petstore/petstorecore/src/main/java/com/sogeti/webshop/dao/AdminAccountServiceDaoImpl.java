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
package com.sogeti.webshop.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sogeti.webshop.model.Admin;

/**
 * impl class for AdminAccountServiceDao
 *
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
@Stateless
public class AdminAccountServiceDaoImpl implements AdminAccountServiceDao
{

   @PersistenceContext
   private EntityManager entityManager;

   /**
    * Find admin.
    *
    * @param loginname the loginname
    * @param password the password
    * @return the admin
    */
   @Override
   public Admin findAdmin(final String loginname, final String password)
   {

      TypedQuery<Admin> typedQuery = this.entityManager.createNamedQuery(Admin.FIND_BY_LOGIN_PASSWORD, Admin.class);
      typedQuery.setParameter("loginname", loginname);
      typedQuery.setParameter("password", password);

      try
      {
         return typedQuery.getSingleResult();
      }
      catch (NoResultException e)
      {
         return null;
      }

   }

   /**
    * Set the entityManager to the specified value.
    *
    * @param entityManager The entityManager to set.
    */
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

   
}
