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
package com.sogeti.webshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.sogeti.webshop.model.Category;

/**
 * Impl class for CategoryServiceDao
 *
 * @author nitshah
 */
@Stateless
public class CategoryServiceDaoImpl implements CategoryServiceDao
{

   @PersistenceContext
   private EntityManager entityManager;

   /**
    * Method return all categories.
    *
    * @return List<Category>
    */
   @Override
   public List<Category> getAllCategories()
   {

      CriteriaQuery<Category> criteria = this.entityManager.getCriteriaBuilder().createQuery(Category.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Category.class))).getResultList();

   }

   /**
    * Save category.
    *
    * @param category the category
    */
   @Override
   public void saveCategory(Category category)
   {
      this.entityManager.persist(category);

   }

   /**
    * Update category.
    *
    * @param category the category
    */
   @Override
   public void updateCategory(Category category)
   {
      this.entityManager.merge(category);

   }

   /**
    * Gets the all categories.
    *
    * @return the all categories
    */
   @Override
   public Category findCategory(Long id)
   {
      return this.entityManager.find(Category.class, id);
   }

   /**
    * @param entityManager the entityManager to set
    */
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

}
