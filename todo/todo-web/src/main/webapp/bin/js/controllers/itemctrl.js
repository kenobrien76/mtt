module.controller("ItemCtrl",function($scope, ItemService) {
	$scope.items = [];

	$scope.newItem={};

	$scope.editable = {};

	
	var readAll = function(){
		ItemService.readAll(function(data){
			$scope.items = data;
		});
	}

	$scope.add = function(){
		ItemService.add($scope.newItem, function(data){
			readAll();
		})
	}

	$scope.delete = function(ItemId){
		ItemService.delete(ItemId, function(data){
			readAll();
		})
	}

	$scope.edit = function(){
		ItemService.edit($scope.editable,function(data){
			readAll();
		})
	}
	$scope.setEditable = function(item){
		$scope.editable = item;
	}

	readAll();
});

