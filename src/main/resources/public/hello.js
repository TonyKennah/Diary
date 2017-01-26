	var items = angular.module('items', []);
	var people = angular.module('person', []);
	var orders = angular.module('orders', []);
	var special = angular.module('special', []);
	var app = angular.module('app', ['person', 'items', 'orders', 'special']);
		
	people.controller('custsCtrl', function($scope, $http) 
	{
	    $http.get("http://localhost:8082/person").then(function(cust) 
	    {
	        $scope.person = cust.data._embedded.person;
	        $scope.personsearch = cust.data._links.search.href;
	    });
	});
	
	items.controller('itemsCtrl', function($scope, $http) 
	{
	    $http.get("http://localhost:8082/items").then(function(item) 
	    {
	        $scope.items = item.data._embedded.items;
	        $scope.itemssearch = item.data._links.search.href;
	    });
	});	
	
	orders.controller('ordersCtrl', function($scope, $http) 
	{
		$http.get("http://localhost:8082/orders").then(function(order) 
		{
			$scope.orders = order.data._embedded.orders;
			$scope.orderssearch = order.data._links.search.href;
		});
	});
	
	special.controller('specialCtrl', function($scope, $http) 
	{
		$http.get("http://localhost:8082/person/1").then(function(here) 
		{
			here.data.id=1;
			here.data.firstName="Tony";
			here.data.lastName="Kennah";
			$http.patch("http://localhost:8082/person/1", here.data).then(function(fyi) 
			{
				$scope.msg = 'Cool Data Saved';
			});
		});
	});