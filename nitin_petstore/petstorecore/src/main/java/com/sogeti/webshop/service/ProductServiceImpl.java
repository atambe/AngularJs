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

import com.sogeti.webshop.dao.ProductServiceDao;
import com.sogeti.webshop.model.Product;

/**
 * Impl class for ProductService
 * 
 * @author nitshah
 */
@Stateless
public class ProductServiceImpl implements ProductService
{

   @Inject
   private ProductServiceDao productServiceDao;

   /**
    * Method return list of product by product Id and also apply business logic if any
    * 
    * @param Long categoryId
    * @return List<Product>
    */
   @Override
   public List<Product> getProductsByCategory(Long categoryId)
   {

      return this.productServiceDao.getProductsByCategory(categoryId);
   }

   /**
    * method use to find product By product ID and also apply business logic if any
    * 
    * @param Long productId
    * @return Product Object
    */
   @Override
   public Product findProduct(Long productId)
   {
      return this.productServiceDao.findProduct(productId);
   }

   /**
    * this method is use for save Product
    * @param product
    */
   @Override
   public void saveProduct(Product product)
   {
      this.productServiceDao.saveProduct( product);
      
   }

   /**
    * this method is use for update Product
    * @param product
    */
   @Override
   public void updateProduct(Product product)
   {
      this.productServiceDao.updateProduct( product);
   }

   /**
    * this method will return all product list 
    * 
    * @return List<Product>
    */
   @Override
   public List<Product> getAllProduct()
   {
      return  this.productServiceDao.getAllProduct();
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
      return this.productServiceDao.searchProducts(keyword);
   }
   
   /**
    * @param productServiceDao the productServiceDao to set
    */
   public void setProductServiceDao(ProductServiceDao productServiceDao)
   {
      this.productServiceDao = productServiceDao;
   }

}
