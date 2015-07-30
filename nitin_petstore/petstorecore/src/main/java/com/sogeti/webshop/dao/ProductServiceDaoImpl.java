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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import com.sogeti.webshop.model.Product;

/**
 * Impl Class of ProductServiceDao
 *
 * @author nitshah
 */
@Stateless
public class ProductServiceDaoImpl implements ProductServiceDao
{

   @PersistenceContext
   private EntityManager entityManager;

   /**
    * Method return list of product by product Id
    *
    * @param Long categoryId
    * @return List<Product>
    */
   @Override
   public List<Product> getProductsByCategory(@NotNull Long categoryId)
   {
      TypedQuery<Product> productBytypedCategoryIdQuery = this.entityManager.createNamedQuery(Product.FIND_BY_CATEGORY_ID, Product.class);
      productBytypedCategoryIdQuery.setParameter("categoryId", categoryId);
      return productBytypedCategoryIdQuery.getResultList();
   }

   /**
    * method use to find product By product ID
    *
    * @param Long productId
    * @return Product Object
    */
   @Override
   public Product findProduct(Long productId)
   {

      return this.entityManager.find(Product.class, productId);
   }
   

   /**
    * Save product.
    * 
    * @param product the product
    */
   @Override
   public void saveProduct(Product product)
   {
      this.entityManager.persist(product);
      
   }
   
   /**
    * Update product.
    * 
    * @param product the product
    */
   @Override
   public void updateProduct(Product product)
   {
      this.entityManager.merge(product);
      
   }

   /**
    * Gets the all categories.
    * 
    * @return the all categories
    */
   @Override
   public List<Product> getAllProduct()
   {
      CriteriaQuery<Product> criteria = this.entityManager.getCriteriaBuilder().createQuery(Product.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Product.class))).getResultList();

   }
   
   /**
    * Search products.
    * 
    * @param keyword the keyword
    * @return the list
    */
   @Override
   public List<Product> searchProducts(String keyword)
   {
      if (keyword == null) {
         keyword = "";
      }
      TypedQuery<Product> productSearchQuery = this.entityManager.createNamedQuery(Product.SEARCH, Product.class);
      productSearchQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
      return productSearchQuery.getResultList();
   }

   /**
    * @param entityManager the entityManager to set
    */
   public void setEntityManager(EntityManager entityManager)
   {
      this.entityManager = entityManager;
   }

}
