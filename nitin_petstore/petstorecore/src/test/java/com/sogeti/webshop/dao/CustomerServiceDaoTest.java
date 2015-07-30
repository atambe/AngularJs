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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sogeti.webshop.model.Address;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.OrderLine;
import com.sogeti.webshop.model.Product;
import com.sogeti.webshop.model.PurchaseOrder;

/**
 * Test Class for CustomerServiceDao
 *
 * @author nitshah (c) Oct 17, 2014, Sogeti B.V.
 */
public class CustomerServiceDaoTest
{

   private CustomerServiceDaoImpl testObj;

   @Mock
   private EntityManager entityManager;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new CustomerServiceDaoImpl();

      this.testObj.setEntityManager(this.entityManager);
   }

   /**
    * test method for findCustomer with ID
    */
   @Test
   public void testFindCustomerWithID()
   {
      this.testObj.findCustomer(1L);

      verify(this.entityManager).find((Class<Customer>) anyObject(), anyObject());
   }

   /**
    * test method for doesUserLogin
    */
   @Test
   public void testDoesUserLogin()
   {
      TypedQuery<Customer> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.createNamedQuery(Customer.FIND_BY_LOGIN, Customer.class)).thenReturn(typedQuery);

      // when user found
      boolean result = this.testObj.doesUserLogin("test");

      assertEquals(true, result);

      // when user not found
      doThrow(new NoResultException()).when(typedQuery).getSingleResult();

      result = this.testObj.doesUserLogin("test");

      assertEquals(false, result);
   }

   /**
    * test method for createCustomer
    */
   @Test
   public void testCreateCustomer()
   {
      Customer customer = getCustomer();

      Customer result = this.testObj.createCustomer(customer);

      assertEquals(customer, result);
      verify(this.entityManager).persist(any(Customer.class));
   }

   /**
    * test method for findCustomer user credential
    */
   @Test
   public void testFindCustomer()
   {
      Customer customer = getCustomer();
      TypedQuery<Customer> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.createNamedQuery(Customer.FIND_BY_LOGIN_PASSWORD, Customer.class)).thenReturn(typedQuery);
      when(typedQuery.getSingleResult()).thenReturn(customer);

      // when user found
      Customer result = this.testObj.findCustomer("test", "test");
      assertEquals(customer, result);

      // when user not found
      doThrow(new NoResultException()).when(typedQuery).getSingleResult();

      result = this.testObj.findCustomer("test", "test");
      assertEquals(null, result);
   }

   /**
    * test method for updateCustomer
    */
   @Test
   public void testUpdateCustomer()
   {
      Customer customer = getCustomer();

      this.testObj.updateCustomer(customer);
      verify(this.entityManager).merge(customer);
   }

   /**
    * test method for getAllCustomer
    */
   @Test
   public void testGetAllCustomer()
   {
      CriteriaQuery<Customer> criteria = Mockito.mock(CriteriaQuery.class);
      CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
      Root<Customer> root = Mockito.mock(Root.class);
      CriteriaQuery<Customer> criteria1 = Mockito.mock(CriteriaQuery.class);
      TypedQuery<Customer> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
      when(criteriaBuilder.createQuery(Customer.class)).thenReturn(criteria);
      when(criteria.from(Customer.class)).thenReturn(root);
      when(criteria.select(root)).thenReturn(criteria1);
      when(this.entityManager.createQuery(criteria1)).thenReturn(typedQuery);
      when(this.entityManager.getCriteriaBuilder().createQuery(Customer.class)).thenReturn(criteria);
      when(typedQuery.getResultList()).thenReturn(getDummyCustomers());

      List<Customer> resultList = this.testObj.getAllCustomer();

      assertEquals(1, resultList.size());
   }
   
   /**
    * test method for getOrderHistoryByCustId
    */
   @Test
   public void testGetOrderHistoryByCustId(){
      TypedQuery<PurchaseOrder> typedQuery = Mockito.mock(TypedQuery.class);
      
      when(this.entityManager.createNamedQuery(PurchaseOrder.FIND_BY_CUSTOMER_ID, PurchaseOrder.class)).thenReturn(typedQuery);
      when(typedQuery.getResultList()).thenReturn(getDummyPurchaseOrders());
      
      List<PurchaseOrder> resultList = this.testObj.getOrderHistoryByCustId(1l);
      
      verify(typedQuery).setParameter("customerId", 1l);
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

   private List<Customer> getDummyCustomers()
   {
      List<Customer> returnList = new ArrayList<Customer>();

      returnList.add(getCustomer());

      return returnList;
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
      ///returnObj.setCategory(mockCategory);

      return returnObj;
   }
}
