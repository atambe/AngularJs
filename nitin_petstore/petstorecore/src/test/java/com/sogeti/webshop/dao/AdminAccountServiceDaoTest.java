/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 17, 2014 Sogeti Nederland B.V. All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Sogeti Nederland B.V.            |  No part of this file may be reproduced
 ** Distributed Software Engineering |  or transmitted in any form or by any
 ** Lange Dreef 17                   |  means, electronic or mechanical, for the
 ** 4131 NJ Vianen                   |  purpose, without the express written
 ** The Netherlands                  |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 */
package com.sogeti.webshop.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sogeti.webshop.model.Admin;

/**
 * Test Class for CustomerServiceDao
 *
 * @author nitshah (c) Oct 17, 2014, Sogeti B.V.
 */
public class AdminAccountServiceDaoTest
{
   private AdminAccountServiceDaoImpl testObj;

   @Mock
   private EntityManager entityManager;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new AdminAccountServiceDaoImpl();

      this.testObj.setEntityManager(this.entityManager);
   }

   /**
    * test method for findAdmin
    */
   @Test
   public void testFindAdmin()
   {

      TypedQuery<Admin> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.createNamedQuery(Admin.FIND_BY_LOGIN_PASSWORD, Admin.class)).thenReturn(typedQuery);

      // when admin user found
      when(typedQuery.getSingleResult()).thenReturn(getAdmin());
      Admin result = this.testObj.findAdmin("admin", "admin");

      assertEquals("admin", result.getLoginname());
      verify(typedQuery).getSingleResult();

      // when admin user not found

      doThrow(new NoResultException()).when(typedQuery).getSingleResult();

      result = this.testObj.findAdmin("admin", "admin");

      assertEquals(null, result);
   }

  // dummy data 
   private Admin getAdmin()
   {
      Admin returnObj = new Admin();
      returnObj.setId(1L);
      returnObj.setLoginname("admin");
      returnObj.setPassword("admin");

      return returnObj;
   }
}
