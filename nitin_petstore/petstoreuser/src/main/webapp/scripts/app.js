'use strict';

// declare modules
angular.module('Shopping', []);
angular.module('Authentication', []);

angular.module('PetSuppliesApp', [
	'Authentication',
    'Shopping',
    'ngRoute',
    'ngCookies',
    'ngAnimate'
])

.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/home', {
            controller: 'homeController',
            templateUrl: 'modules/shopping/views/home.html',
            hideMenus: true
        })  
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })
        
        .when('/logout', {
            controller: 'logoutCtrl',
            templateUrl: 'modules/shopping/views/home.html',
            hideMenus: true
        })
        
        .otherwise({ redirectTo: '/home' });
}])

.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
      

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
//            if ($location.path() !== '/home' /*&& !$rootScope.globals.currentUser*/) {
//                $location.path('/home');
//            }
        });
    }]);