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
package com.sogeti.webshop.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sogeti.webshop.dao.AdminAccountServiceDao;
import com.sogeti.webshop.model.Admin;

/**
 * impl for AdminAccountService
 *
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
@Stateless
public class AdminAccountServiceImpl implements AdminAccountService
{

   @Inject
   private AdminAccountServiceDao adminAccountServiceDao;

   @Override
   public Admin findAdmin(final String loginname, final String password)
   {
      return this.adminAccountServiceDao.findAdmin(loginname, password);
   }

   /**
    * Set the adminAccountServiceDao to the specified value.
    *
    * @param adminAccountServiceDao The adminAccountServiceDao to set.
    */
   public void setAdminAccountServiceDao(AdminAccountServiceDao adminAccountServiceDao)
   {
      this.adminAccountServiceDao = adminAccountServiceDao;
   }
      
}
