module.controller("TodoCtrl",function($scope, LoginService, $location) {
	$scope.sdo = {};

	$scope.go = function ( path ) {
		$location.path( path );
	};

	$scope.isActive = function(route) {
        return route === $location.path();
    }
	
	LoginService.getUserDetails(function(data){
		$scope.sdo = data;
	},
	function(){
		//window.location.href = "/admin/spring_security_login";
	});
});
