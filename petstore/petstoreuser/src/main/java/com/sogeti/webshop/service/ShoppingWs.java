package com.sogeti.webshop.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Product;

@Path("/shopping")
public class ShoppingWs {
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	ProductService productService;
	
	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategorieList(){
		return categoryService.getAllCategories();
	}
	
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductList(){
		return productService.getAllProduct();
	}
	
	@GET
	@Path("/getProductsByCategory/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsByCategory( @PathParam("categoryId") String categoryId){
		
		if(StringUtils.isBlank(categoryId)){
			return null;
		}
		
		Long paramCategoryId= Long.parseLong(categoryId);
		
		return productService.getProductsByCategory(paramCategoryId);
	}
	
	@GET
	@Path("/searchProduct/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> searchProducts( @PathParam("keyword") String keyword){
		return productService.searchProducts(keyword);
	}
}
