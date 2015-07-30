/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 16, 2014 Sogeti Nederland B.V. All Rights Reserved.
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.sogeti.webshop.dao.CustomerServiceDao;
import com.sogeti.webshop.model.Address;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.OrderLine;
import com.sogeti.webshop.model.Product;
import com.sogeti.webshop.model.PurchaseOrder;

/**
 * Test Class for CustomerService
 *
 * @author nitshah (c) Oct 17, 2014, Sogeti B.V.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest
{

   private CustomerServiceImpl testObj;

   @Mock
   private CustomerServiceDao customerServiceDao;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new CustomerServiceImpl();

      this.testObj.setCustomerServiceDao(this.customerServiceDao);

   }

   /**
    * Test method for findCustomer() by ID
    */
   @Test
   public void testFindCustomerById()
   {

      when(this.customerServiceDao.findCustomer(1l)).thenReturn(getCustomer());

      Customer result = this.testObj.findCustomer(1l);

      assertNotNull(result);
      assertEquals("TestFirstName", result.getFirstname());
   }

   /**
    * Test method for doesUserLogin()
    */
   @Test
   public void testDoesUserLogin()
   {
      when(this.customerServiceDao.doesUserLogin("test")).thenReturn(true);

      boolean result = this.testObj.doesUserLogin("test");

      assertEquals(true, result);
   }

   /**
    * Test method for createCustomer()
    */
   @Test
   public void testCreateCustomer()
   {
      Customer customer = getCustomer();
      when(this.customerServiceDao.createCustomer(customer)).thenReturn(customer);

      Customer result = this.testObj.createCustomer(customer);

      assertEquals(customer, result);
      verify(this.customerServiceDao).createCustomer(customer);
   }

   /**
    * Test method for findCustomer() by login credential
    */
   @Test
   public void testFindCustomer()
   {

      when(this.customerServiceDao.findCustomer("test", "test")).thenReturn(getCustomer());

      Customer result = this.testObj.findCustomer("test", "test");

      assertNotNull(result);
      assertEquals("TestFirstName", result.getFirstname());
   }

   /**
    * Test method for updateCustomer()
    */
   @Test
   public void testUpdateCustomer()
   {
      Customer customer = getCustomer();

      this.testObj.updateCustomer(customer);

      verify(this.customerServiceDao).updateCustomer(customer);
   }

   /**
    * Test method for getAllCustomer()
    */
   @Test
   public void testGetAllCustomer()
   {
      when(this.customerServiceDao.getAllCustomer()).thenReturn(getCustomers());

      List<Customer> resultList = this.testObj.getAllCustomer();

      assertEquals(1, resultList.size());
   }
   
   /**
    * Test method for getOrderHistoryByCustId()
    */
   @Test
   public void testGetOrderHistoryByCustId(){
      when(this.customerServiceDao.getOrderHistoryByCustId(1l)).thenReturn(getDummyPurchaseOrders());
      
      List<PurchaseOrder> resultList= this.testObj.getOrderHistoryByCustId(1l);
      
      assertEquals(1, resultList.size());
   }

   // dummy data 
   
   private List<PurchaseOrder> getDummyPurchaseOrders()
   {
      List<PurchaseOrder> returnList = new ArrayList<PurchaseOrder>();
      returnList.add(getOrder());
      
      return returnList;
   }
   
   private PurchaseOrder getOrder()
   {
      PurchaseOrder returnObj = new PurchaseOrder();

      returnObj.setCustomer(getCustomer());
      returnObj.setDeliveryAddress(getAddress());
      returnObj.setOrderLines(getOrderLines());

      return returnObj;
   }

   private List<OrderLine> getOrderLines()
   {
      List<OrderLine> returnList = new ArrayList<OrderLine>();

      returnList.add(getOrderLine());

      return returnList;
   }

   private OrderLine getOrderLine()
   {
      OrderLine returnObj = new OrderLine();

      returnObj.setProduct(getDummyProduct());
      returnObj.setQuantity(1);

      return returnObj;
   }

   private List<Customer> getCustomers()
   {
      List<Customer> returnList = new ArrayList<Customer>();
      returnList.add(getCustomer());

      return returnList;
   }
   
   private Product getDummyProduct()
   {
      Product returnObj = new Product();
      Category mockCategory = Mockito.mock(Category.class);

      returnObj.setId(1004l);
      returnObj.setName("Pet Carrier");
      returnObj.setDescription("N2N PetHouse Pet Carrier.");
      returnObj.setUnitCost(new BigDecimal("3.10"));
     // returnObj.setCategory(mockCategory);

      return returnObj;
   }

   private Customer getCustomer()
   {
      Customer returnObj = new Customer();

      returnObj.setId(1l);
      returnObj.setFirstname("TestFirstName");
      returnObj.setLastname("TestLAstName");
      returnObj.setMobile("789456123");
      returnObj.setEmail("Test@test.com");
      returnObj.sethomeAddress(getAddress());

      return returnObj;
   }

   private Address getAddress()
   {
      Address returnObj = new Address();

      returnObj.setCity("TestCity");
      returnObj.setStreet("TestStreet");
      returnObj.setZipcode("456778");
      returnObj.setCountry("TestCountry");

      return returnObj;
   }
}
