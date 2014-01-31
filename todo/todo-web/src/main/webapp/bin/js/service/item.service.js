services.service('ItemService', function($http, $rootScope, $q) {
	var ItemService = {
			url:'/todo/rest/items',
			readAll : function(callback){
				$http({
					method:'GET',
					url:this.url
				})
				.success(function(data){
					callback(data);
				});
			},
			delete : function(ItemId, callback){
				$http({
					method : 'DELETE',
					url : this.url + '/' + ItemId,
					headers : {
						'Content-Type' : 'application/json'
					}
				})
				.success(function(data){
					callback(data);
				})
			},
			add : function(Item, callback){
				$http({
					method:'POST',
					url:this.url,
					data : Item
				})
				.success(function(data){
					callback(data);
				});
			},
			edit : function(item, callback){
				$http({
					method:'PUT',
					url:this.url + '/' + item.id,
					data : item
				})
				.success(function(data){
					callback(data);
				});
			}

	};
	return ItemService;
});