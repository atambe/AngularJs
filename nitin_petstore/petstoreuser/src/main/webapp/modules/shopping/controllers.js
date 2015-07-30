'use strict';

/**
 * controller for Shopping Module.
 */
angular.module('Shopping',[])

.controller('homeController',
        function ($scope, $http, $location,$cookieStore,$rootScope,$routeParams,ShoppingService){
		
		var fullUrl= $location.absUrl();
		var baseLen = $location.absUrl().length - $location.url().length-2;
		$rootScope.appUrl=fullUrl.substring(0,baseLen);
	
		/**
		 * Load All Categories
		 */
		ShoppingService.loadAllCategories(function (response) {			
			$scope.categories = response;
		});
		
		/**
		 * Load All Products
		 */
		ShoppingService.loadAllProducts(function (response) {			
			$scope.products = response;
		});
	    
		
		/**
		 * function to get products by  selected Category
		 */
		$scope.getProductsByCategory = function (selectedCategory) {
			$scope.hideShoppingCart();
			
			ShoppingService.getProductsByCategory(selectedCategory,function (response) {			
			  	$scope.products = response;});
		},
		
		/**
		 * function to search products by keyword
		 */
		$scope.searchProduct = function () {
			$scope.hideShoppingCart();
			ShoppingService.searchProduct($scope.searchKeyWord,function (response) {			
	    	$scope.products = response;});
		},
		
		/**
		 * function to create PO
		 */
	    $scope.createOrder= function() {
			ShoppingService.createOrder($rootScope.invoice,function (response) {			
				
				if (response) {
		    		$rootScope.invoice=null;
		    		$scope.hideShoppingCart();
		    		$scope.showInfoMsg=true;	    		
		    		$scope.infoMsg="Thank You for Shopiing "+response.customer.loginname+" !!!!! Your Purchase Order Number is "+response.id;
				}else{
					$scope.error = 'Problem with creating Purchse Order. Contact system admin for more info';
				}
	    	});
	    }

		/**
		 * function to add product in cart
		 */
		$scope.addToCart = function (selectedItem) {
			$scope.showshoppingCart();
			 
			if(angular.isUndefined($rootScope.invoice) || $rootScope.invoice === null
					|| angular.isUndefined($rootScope.invoice.items) || $rootScope.invoice.items === null){
				
				$rootScope.invoice = {
				        items: [{
				        	id:selectedItem.id,
				        	qty: 1,
				            name: selectedItem.name,
				            cost: selectedItem.unitCost,
				            product:selectedItem
				            }]
				    };
			}else{
				$scope.validateAndAddItem(selectedItem);				
			}
			
		},	

		/**
		 * function to remove product in cart
		 */
	    $scope.removeItem = function(index) {
			$rootScope.invoice.items.splice(index, 1);
	    },	 
	    
	    /**
		 * function to total of invoice
		 */
	    $scope.total = function() {
	        var total = 0;
	        angular.forEach($rootScope.invoice.items, function(item) {
	            total += item.qty * item.cost;
	        })

	        return total;
	    },
	    	
	    
	    /**
		 * Utility function to validate and add item to cart
		 */
	    $scope.validateAndAddItem = function(selectedItem) {
	    	var itemfound = false;
	        angular.forEach($rootScope.invoice.items, function(item) {
	            if(selectedItem.id === item.id){
	            	itemfound = true;
	            	item.qty+=1;
	            }
	        })
	        
	        if(!itemfound){
	        	$rootScope.invoice.items.push({
	        		id:selectedItem.id,
		            qty: 1,
		            name: selectedItem.name,
		            cost: selectedItem.unitCost,
		            product:selectedItem
		        });
	        }
	    },
	    
	    /**
		 * Utility function to show shopping cart
		 */
	    $scope.showshoppingCart= function() {
	    	
	    	 $scope.subheading="Shopping Cart";
	    	 $scope.showProducts=false;
			 $scope.showCart=true;
			 $scope.checkout=false;
			 $scope.showInfoMsg=false;
						 
	    },	
	    
	    /**
		 * Utility function to hide shopping cart
		 */
	    $scope.hideShoppingCart= function() {
	    	 $scope.subheading="Products List";
	    	 $scope.showProducts=true;
			 $scope.showCart=false;
			 $scope.checkout=false;
			 $scope.showInfoMsg=false;
	    },
	    	    
	    /**
		 * Utility function to show checkout page
		 */
	    $scope.showCheckout= function() {
	    	$scope.showProducts=false;
			$scope.showCart=false;
	    	$scope.checkout=true;
	    	$scope.showInfoMsg=false;
	    }
	    
	    /**
		 * Utility function to hide checkout page
		 */
	    $scope.hideCheckout= function() {
	    	$scope.showProducts=false;
			$scope.showCart=true;
	    	$scope.checkout=false;
	    	$scope.showInfoMsg=false;
	    }

	    /**
	     * On controller load
	     */
		$scope.subheading="Products List";
		$scope.showProducts=true;
		$scope.showCart=false;
		$scope.checkout=false;
		$scope.showInfoMsg=false;
		
		if (!angular.isUndefined($cookieStore.get("globals"))){	

			$rootScope.currentUser=$cookieStore.get("globals").currentUser;
			$rootScope.isUserLogedIn=true;
		}
		
		

});



