package com.angular.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.angular.core.entity.ProductBeans;
import com.angular.core.impservice.CommonUtility;
import com.angular.core.impservice.ProductDisplayService;

@RestController
public class CustomerController {
	
	@Autowired
	private ProductDisplayService productList;
	@Autowired
	private CommonUtility authentication;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public @ResponseBody List<ProductBeans> getShopInJSON() 
	{
	    return productList.getProducts();
	}
	
	@RequestMapping(value = "/products", method=RequestMethod.GET)
	public @ResponseBody List<ProductBeans> getProducts(@RequestParam("categoryName") String productName){
		System.out.println("Product Namer::::: "+productName);
		System.out.println("Inside Controller");
	    return productList.getProductName(productName);
	}
	
	@RequestMapping(value = "/productsCategory", method=RequestMethod.GET)
	public @ResponseBody List<ProductBeans> getProductsCategory(@RequestParam("categoryID") String group){
		 return productList.sortByGroup(Integer.parseInt(group));
	}
	
	@RequestMapping(value = "/productsInfo", method=RequestMethod.GET)
	public @ResponseBody List<ProductBeans> getProductsInfo(@RequestParam("proID") String group){
		 return productList.getProductListByID(Integer.parseInt(group));
	}
	
	@RequestMapping(value = "/login/{email}/{pass}", method=RequestMethod.POST)
	public @ResponseBody String loginValidation(@PathVariable("email") String email,@PathVariable("pass") String password){
		 
		 StringBuilder builder = new StringBuilder();
		 builder.append("{\"result\":\"");
		 builder.append(authentication.authenticateUser(email, password));
		 builder.append("\"}");		 
		 return builder.toString();
	}
}
