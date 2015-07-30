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

import com.sogeti.webshop.dao.CategoryServiceDao;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Product;

/**
 * Test Class for CategoryService
 *
 * @author nitshah
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest
{

   CategoryServiceImpl testObj;

   @Mock
   CategoryServiceDao categoryServiceDao;

   /**
    * set up
    */
   @Before
   public void setup()
   {
      MockitoAnnotations.initMocks(this);
      this.testObj = new CategoryServiceImpl();

      this.testObj.setCategoryServiceDao(this.categoryServiceDao);
   }

   /**
    * Test method for getAllCategories()
    */
   @Test
   public void testGetAllCategories()
   {

      when(this.categoryServiceDao.getAllCategories()).thenReturn(getDummyCategories());

      List<Category> result = this.testObj.getAllCategories();

      assertNotNull(result);
      assertEquals(1, result.size());
   }

   /**
    * Test method for saveCategory()
    */
   @Test
   public void testSaveCategory()
   {

      this.testObj.saveCategory(getDummyCategory());

      verify(this.categoryServiceDao).saveCategory(any(Category.class));
   }

   /**
    * Test method for updateCategory()
    */
   @Test
   public void testUpdateCategory()
   {

      this.testObj.updateCategory(getDummyCategory());

      verify(this.categoryServiceDao).updateCategory(any(Category.class));
   }

   /**
    * Test method for findCategory()
    */
   @Test
   public void testFindCategory()
   {
      when(this.categoryServiceDao.findCategory(001l)).thenReturn(getDummyCategory());

      Category result = this.testObj.findCategory(001l);

      assertNotNull(result);
      assertEquals("Dog", result.getName());
   }

   //Dummy Data

   private List<Category> getDummyCategories()
   {

      List<Category> returnObj = new ArrayList<Category>();
      returnObj.add(getDummyCategory());

      return returnObj;
   }

   private Category getDummyCategory()
   {
      Category returnObj = new Category();

      returnObj.setId(001l);
      returnObj.setName("Dog");
    //  returnObj.setProducts(getDummyProducts());

      return returnObj;
   }

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
