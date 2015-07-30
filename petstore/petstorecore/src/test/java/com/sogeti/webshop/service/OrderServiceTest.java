/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 10, 2014 Sogeti Nederland B.V. All Rights Reserved.
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

import com.sogeti.webshop.dao.OrderServiceDao;
import com.sogeti.webshop.model.Address;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.OrderLine;
import com.sogeti.webshop.model.Product;
import com.sogeti.webshop.model.PurchaseOrder;
import com.sogeti.webshop.model.ShoppingCartItem;

/**
 * Test Class for OrderService
 *
 * @author nitshah
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest
{

   OrderServiceImpl testObj;

   @Mock
   OrderServiceDao orderServiceDao;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new OrderServiceImpl();

      this.testObj.setOrderServiceDao(this.orderServiceDao);

   }

   /**
    * Test method for createOrder()
    */
   @Test
   public void testCreateOrder()
   {
      Customer customer = getCustomer();
      List<ShoppingCartItem> cartItems = getCartItems();

      when(this.orderServiceDao.createOrder(customer, cartItems)).thenReturn(getOrder());

      PurchaseOrder result = this.testObj.createOrder(customer, cartItems);

      assertNotNull(result);

      assertEquals(1, result.getOrderLines().size());
   }

   // dummy data

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

   private Product getDummyProduct()
   {
      Product returnObj = new Product();
      Category mockCategory = Mockito.mock(Category.class);

      returnObj.setId(1004l);
      returnObj.setName("Pet Carrier");
      returnObj.setDescription("N2N PetHouse Pet Carrier.");
      returnObj.setUnitCost(new BigDecimal("3.10"));
      //returnObj.setCategory(mockCategory);

      return returnObj;
   }

   private List<ShoppingCartItem> getCartItems()
   {
      List<ShoppingCartItem> retrunObj = new ArrayList<ShoppingCartItem>();
      retrunObj.add(getShoppingCartItem());

      return retrunObj;
   }

   private ShoppingCartItem getShoppingCartItem()
   {
      ShoppingCartItem returnObj = new ShoppingCartItem(getDummyProduct(), 1);

      return returnObj;
   }
}
