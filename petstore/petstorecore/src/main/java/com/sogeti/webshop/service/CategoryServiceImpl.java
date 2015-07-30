/*------------------------------------------------------------------------------
 **     Ident: Delivery Center Java
 **    Author: nitshah
 ** Copyright: (c) Oct 7, 2014 Sogeti Nederland B.V. All Rights Reserved.
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

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sogeti.webshop.dao.CategoryServiceDao;
import com.sogeti.webshop.model.Category;

@Stateless
public class CategoryServiceImpl implements CategoryService
{

   @Inject
   private CategoryServiceDao categoryServiceDao;

   /**
    * Method return all categories and also apply business logic if any.
    * 
    * @return List<Category>
    */
   @Override
   public List<Category> getAllCategories()
   {

      return this.categoryServiceDao.getAllCategories();
   }
   
   /**
    * Method is saving  category and also apply business logic if any.
    * 
    * @param Category object
    */
   public void saveCategory(Category category)
   {
      categoryServiceDao.saveCategory(category);
      
   }

   /**
    * Method is updateing category and also apply business logic if any.
    * 
    * @param Category object
    */
   public void updateCategory(Category category)
   {
      categoryServiceDao.updateCategory(category);
      
   }

   /**
    * Method find Category by id  and also apply business logic if any.
    * 
    * @param Long
    * @return Category
    */
   public Category findCategory(Long id)
   {
      return categoryServiceDao.findCategory(id);
   }

   /**
    * @param categoryServiceDao the categoryServiceDao to set
    */
   public void setCategoryServiceDao(CategoryServiceDao categoryServiceDao)
   {
      this.categoryServiceDao = categoryServiceDao;
   }
   
   

}
