var PetShop = angular.module('PetShop', [ 'ngRoute','ngStorage','ngCookies']).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'allProducts.html',
				controller : 'customersController'
			}).when('/login', {
				templateUrl : 'login.html',	
				controller : 'loginController'
			}).when('/search/:searchKey?',{
				templateUrl : 'searchProducts.html',
				controller : 'searchController'
			}).when('/myCart/',{
				templateUrl : 'myCart.html',
				controller : 'storeQuantity'
			}).when('/category/:group?',{
				templateUrl : 'categoryDisplay.html',
				controller : 'categoryController'				
			}).when('/infoPage/:info?',{				
				templateUrl : 'infoProducts.html',
				controller : 'infoController'
			}).otherwise({
				redirectTo : '/home'
			});
		} ]);

