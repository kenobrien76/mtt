services.service('LoginService', function($http, $rootScope, $q) {
	var testService = {
			url:'/todo/rest/users/current',
			getUserDetails : function(callback, err_callback){
				$http({
					method:'GET',
					url:this.url
				})
				.success(function(data){
					callback(data);
				})
				.error(function(data){
					err_callback();
				});
			}
	};
	return testService;
});