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
package com.sogeti.webshop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Product;

/**
 * Test Class for ProductServiceDao
 *
 * @author nitshah
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceDaoTest
{

   ProductServiceDaoImpl testObj;

   @Mock
   EntityManager entityManager;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new ProductServiceDaoImpl();

      this.testObj.setEntityManager(this.entityManager);
   }

   /**
    * Test method for getProductsByCategory()
    */
   @Test
   public void testGetProductsByCategory()
   {
      TypedQuery<Product> mockTypedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.createNamedQuery(Product.FIND_BY_CATEGORY_ID, Product.class)).thenReturn(mockTypedQuery);
      when(mockTypedQuery.getResultList()).thenReturn(getDummyProducts());

      List<Product> result = this.testObj.getProductsByCategory(1l);

      assertNotNull(result);
      assertEquals(1, result.size());

   }

   /**
    * Test method for findProduct()
    */
   @Test
   public void testFindProduct()
   {

      when(this.entityManager.find(Product.class, 1l)).thenReturn(getDummyProduct());

      Product result = this.testObj.findProduct(1l);

      assertNotNull(result);
      assertEquals("Pet Carrier", result.getName());

   }

   /**
    * Test method for saveProduct()
    */
   @Test
   public void testSaveProduct()
   {
      this.testObj.saveProduct(getDummyProduct());

      verify(this.entityManager).persist(any(Product.class));
   }

   /**
    * Test method for updateProduct()
    */
   @Test
   public void testUpdateProduct()
   {
      this.testObj.updateProduct(getDummyProduct());

      verify(this.entityManager).merge(any(Product.class));
   }

   /**
    * Test method for getAllProduct()
    */
   @Test
   public void testGetAllProduct()
   {
      CriteriaQuery<Product> criteria = Mockito.mock(CriteriaQuery.class);
      CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
      Root<Product> root = Mockito.mock(Root.class);
      CriteriaQuery<Product> criteria1 = Mockito.mock(CriteriaQuery.class);
      TypedQuery<Product> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
      when(criteria.from(Product.class)).thenReturn(root);
      when(criteria.select(root)).thenReturn(criteria1);
      when(this.entityManager.createQuery(criteria1)).thenReturn(typedQuery);
      when(this.entityManager.getCriteriaBuilder().createQuery(Product.class)).thenReturn(criteria);
      when(typedQuery.getResultList()).thenReturn(getDummyProducts());

      List<Product> result = this.testObj.getAllProduct();

      assertNotNull(result);
      assertEquals(1, result.size());
   }

   /**
    * Test method for searchProducts()
    */
   @Test
   public void testSearchProducts()
   {
      String searchKeyworkd = "test";
      TypedQuery<Product> typedQuery = Mockito.mock(TypedQuery.class);

      when(this.entityManager.createNamedQuery(Product.SEARCH, Product.class)).thenReturn(typedQuery);
      when(typedQuery.getResultList()).thenReturn(getDummyProducts());

      // when search key word is null

      List<Product> result = this.testObj.searchProducts(null);

      assertNotNull(result);
      assertEquals(1, result.size());
      verify(typedQuery).setParameter("keyword", "%%");

      // when search key word is with value

      result = this.testObj.searchProducts(searchKeyworkd);

      assertNotNull(result);
      assertEquals(1, result.size());
      verify(typedQuery).setParameter("keyword", "%" + searchKeyworkd.toUpperCase() + "%");
   }

   // dummy product

   private List<Product> getDummyProducts()
   {

      List<Product> returnObj = new ArrayList<Product>();
      returnObj.add(getDummyProduct());

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
}
