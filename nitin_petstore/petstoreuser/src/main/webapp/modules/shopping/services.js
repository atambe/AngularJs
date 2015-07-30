'use strict';

angular.module('Shopping')


.factory('ShoppingService',
    function ($http, $cookieStore, $rootScope, $location) {
	var service = {};
	
	/**
	 * Load All Categories
	 */
	service.loadAllCategories = function (callback) {
		
		$http.get($rootScope.appUrl+"/rest/shopping/categories")
		    .success(function(response) {
		    	callback(response); 
		 });

    };
    
    /**
	 * Load All Products
	 */
	service.loadAllProducts = function (callback) {	
		
		$http.get($rootScope.appUrl+"/rest/shopping/products")
	  	.success(function(response) {
		    	callback(response); 
		 });

    };
    
    /**
	 * function to get products by  selected Category
	 */
	service.getProductsByCategory = function (selectedCategory,callback) {	
		
		$http.get($rootScope.appUrl+"/rest/shopping/getProductsByCategory/"+selectedCategory)
    	.success(function(response) {
		    	callback(response); 
		 });

    };
    
    /**
	 * function to search products by keyword
	 */
	service.searchProduct = function (searchKeyWord,callback) {	
		
		$http.get($rootScope.appUrl+"/rest/shopping/searchProduct/"+searchKeyWord)
    	.success(function(response) {
		    	callback(response); 
		 });

    };
	
    /**
	 * function to create PO
	 */
	service.createOrder = function (invoice,callback) {	
		
		$http.get($rootScope.appUrl+"/rest/order/createInvoice/"+JSON.stringify(invoice))
    	.success(function(response) {
		    	callback(response); 
		 });

    };
	
	
	
	return service;
	
})