var module = angular.module('todo', ['todo.service', 'todo.directive'])
.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/items', {templateUrl: './bin/partials/items.htm', controller: 'ItemCtrl'}).
	otherwise({redirectTo: '/items'});
}]);