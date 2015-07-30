'use strict';

angular.module('Authentication')

.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
    	$rootScope.currentUser=null;
//        AuthenticationService.ClearCredentials();

        $scope.login = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function (response) {
            	if (response) {
            		
            		AuthenticationService.SetCredentials($scope.username, $scope.password);                    
                    $location.path('/');
                } else {
                    $scope.error = 'Username or password is incorrect';
                    $scope.dataLoading = false;
                }
            });
        };
    }])
    
   .controller('logoutCtrl',
		    ['$scope', '$rootScope', '$location', 'AuthenticationService',
		     function ($scope, $rootScope, $location, AuthenticationService){		    	
		    	$rootScope.currentUser=null;
		        AuthenticationService.ClearCredentials();
		    	$location.path('/');
               
}]);