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
import static org.mockito.Matchers.any;
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

import com.sogeti.webshop.dao.ProductServiceDao;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Product;

/**
 * Test Class for ProductService
 * 
 * @author nitshah
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{

   @Mock
   ProductServiceDao productServiceDao;

   ProductServiceImpl testObj;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new ProductServiceImpl();

      this.testObj.setProductServiceDao(this.productServiceDao);

   }

   /**
    * Test method for getProductsByCategory()
    */
   @Test
   public void testGetProductsByCategory()
   {

      when(this.productServiceDao.getProductsByCategory(1l)).thenReturn(getDummyProducts());

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

      when(this.productServiceDao.findProduct(1l)).thenReturn(getDummyProduct());
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

      verify(this.productServiceDao).saveProduct(any(Product.class));
   }

   /**
    * Test method for updateProduct()
    */
   @Test
   public void testUpdateProduct()
   {

      this.testObj.updateProduct(getDummyProduct());

      verify(this.productServiceDao).updateProduct(any(Product.class));
   }
   
   /**
    * Test method for getAllProduct()
    */
   @Test
   public void testGetAllProducts(){
      when(this.productServiceDao.getAllProduct()).thenReturn(getDummyProducts());
      
      List<Product> result = this.testObj.getAllProduct();
      
      assertEquals(1, result.size());
   }
   
   /**
    * Test method for searchProducts()
    */
   @Test
   public void testSearchProducts(){
      when(this.productServiceDao.searchProducts("test")).thenReturn(getDummyProducts());
      
      List<Product> result = this.testObj.searchProducts("test");
      
      assertEquals(1, result.size());
   }

   // dummy Product

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
