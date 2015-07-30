package com.angular.core.iservice;

import java.util.List;

import com.angular.core.entity.ProductBeans;

public interface IProductDisplayService 
{
	public List<ProductBeans> getProducts();
	public List<ProductBeans> getProductName(String categoryName);
	public List<ProductBeans> sortByGroup(int categoryName);
	public List<ProductBeans> getProductListByID(int proID);
}
