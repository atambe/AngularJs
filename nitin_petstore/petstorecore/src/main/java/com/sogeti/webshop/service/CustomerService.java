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

import java.util.List;

import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.PurchaseOrder;

/**
 * CustomerService interface
 *
 * @author nitshah (c) Oct 14, 2014, Sogeti B.V.
 */
public interface CustomerService
{
   Customer findCustomer(Long id);

   boolean doesUserLogin(final String loginname);

   Customer createCustomer(final Customer customer);

   Customer findCustomer(final String loginname, final String password);

   void updateCustomer(Customer customer);

   List<Customer> getAllCustomer();
   
   List<PurchaseOrder> getOrderHistoryByCustId(final Long customerId);

}
