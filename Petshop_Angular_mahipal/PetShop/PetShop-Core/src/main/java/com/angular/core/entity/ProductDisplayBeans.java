package com.angular.core.entity;

import java.util.List;

import javax.persistence.Entity;


/**
 * this class is used to hold available product list 
 * @author mahijain
 *
 */
@Entity
public class ProductDisplayBeans {

	private List<ProductBeans> productList;

	public List<ProductBeans> getProductList() {
		return productList;
	}
	
	public void setProductList(List<ProductBeans> productList) {
		this.productList = productList;
	}
}
