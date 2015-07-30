/* Controllers */

// This method is used to display products
PetShop.controller('customersController', function($scope, $http) {
	$http.get("http://localhost:8080/PetStore/").success(function(response) {
		$scope.names = response;
	});
});

// This method is used to search products
PetShop.controller('searchController', function($scope, $http, $routeParams) {
	$scope.search = $routeParams.searchKey;
	$http.get(
			"http://localhost:8080/PetStore/products?categoryName="
					+ $scope.search).success(function(response) {
		$scope.searchProduct = response;
	});
});

// This method is used to search category
PetShop.controller('categoryController', function($scope, $http, $routeParams) {
	$scope.search = $routeParams.group;
	$http.get(
			"http://localhost:8080/PetStore/productsCategory?categoryID="
					+ $scope.search).success(function(response) {
		$scope.group = response;
	});
});

// This method is used to display products info page
PetShop.controller('infoController', function($scope, $http, $routeParams) {

	$scope.search = $routeParams.info;
	$http.get("http://localhost:8080/PetStore/productsInfo?proID=" + $scope.search).success(function(response) 
	{
		$scope.info = response;
	});
	
	if (localStorage.getItem("saveditemsincart") == null) {
		$scope.items = [];
	}else{
		$scope.items = JSON.parse(localStorage["saveditemsincart"]);
	}
	// This function is used to maintaining cart
	$scope.validateQuantity = function(obj) {
		var availableQun = parseInt(document.forms["addCart"]["availableQun"].value.trim(), 10);
		var quantity = parseInt(document.forms["buyNow"]["quantity"].value.trim(), 10);
		// Validation Part
		if (quantity == null || quantity == "" || quantity <= 0) {
			alert("Quantity should be one or more");
		} else if (quantity > availableQun) {
			alert("Required quantity not available, Available quantity is " + availableQun);
		} else {	
	        $scope.items.push({
	        	productId: obj.productId,
	        	productName: obj.productName,
	        	price: obj.price,
	            quantity: quantity
	        });  
	        localStorage.setItem('saveditemsincart', JSON.stringify($scope.items));
	        window.location.href = "#/home/";
		}
	};
});

// This is used to save quantity array inside local storage
PetShop.controller( "storeQuantity", function($http, $scope, $localStorage, $routeParams) 
		{
	
					if (localStorage.getItem("saveditemsincart") != null ) {
						$scope.items = JSON.parse(localStorage["saveditemsincart"]);						
					}
					// This method is used to remove products from cart
					$scope.removeProduct = function(obj) {
						for(var i=0; i<$scope.items.length; i++)
							{
								if($scope.items[i].productId == obj.productId)
									{
									$scope.items.splice(i, 1);
									}
								localStorage.setItem('saveditemsincart', JSON.stringify($scope.items));
							}
					};
					// This method is used to update quantity inside cart
					$scope.updateProduct = function (user,obj) {
						if(user == null || user == ""){
							alert("Please provide proper quantity");
						}else{
						for(var i=0; i<$scope.items.length; i++)
						{
							if($scope.items[i].productId == obj.productId)
								{
								$scope.items[i].quantity = user;
								}
							localStorage.setItem('saveditemsincart', JSON.stringify($scope.items));
						}
						}
					};
					
					$scope.total =  function()
					{
						var total = 0;
						for(var i=0; i < $scope.items.length; i++)
						{
							total += $scope.items[i].quantity *  $scope.items[i].price;
						}
						return total;
					};
});

// This controller is used to handle login function
PetShop.controller('loginController', function($cookieStore, $scope, $http, $routeParams ) {
	$scope.status;
	 $scope.myVar = true;
	// This function is used to maintaining login functionality
	$scope.login = function(email,pass) {
		$http.post("http://localhost:8080/PetStore/login/"+email+"/"+pass).success(function(response) {
			 $scope.validationAuth = response;
			 if(response.result != "failed" && response.result != "error")
				 {
				  // Put cookie
				  $cookieStore.put('user',response.result);
				  $scope.status = "Sucess";
				  
				 }
			 else{
				 $scope.status = "Authentication Fail";
				 $scope.myVar = false;
			 }
		});
	};
	
});