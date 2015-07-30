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

import com.sogeti.webshop.model.Product;

/**
 * Product Service Dao Interface
 *
 * @author nitshah
 */
public interface ProductServiceDao
{

   List<Product> getProductsByCategory(Long categoryId);

   Product findProduct(Long productId);

   void saveProduct(Product product);

   void updateProduct(Product product);

   List<Product> getAllProduct();

   List<Product> searchProducts(String keyword);

}
