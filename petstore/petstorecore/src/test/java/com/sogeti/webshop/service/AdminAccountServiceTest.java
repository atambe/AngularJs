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
package com.sogeti.webshop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sogeti.webshop.dao.AdminAccountServiceDao;
import com.sogeti.webshop.model.Admin;

/**
 * ????
 *
 * @version $Id:$
 * @author nitshah (c) Oct 17, 2014, Sogeti B.V.
 */
public class AdminAccountServiceTest
{

   private AdminAccountServiceImpl testObj;

   @Mock
   private AdminAccountServiceDao adminAccountServiceDao;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new AdminAccountServiceImpl();

      this.testObj.setAdminAccountServiceDao(this.adminAccountServiceDao);

   }

   /**
    * Test method for findAdmin()
    */
   @Test
   public void testFindAdmin()
   {
      when(this.adminAccountServiceDao.findAdmin("admin", "admin")).thenReturn(getAdmin());

      Admin result = this.testObj.findAdmin("admin", "admin");

      assertNotNull(result);
      assertEquals("admin", result.getLoginname());
   }

   // dummy data
   private Admin getAdmin()
   {
      Admin returnObj = new Admin();
      returnObj.setId(1l);
      returnObj.setLoginname("admin");
      returnObj.setPassword("admin");

      return returnObj;
   }
}
