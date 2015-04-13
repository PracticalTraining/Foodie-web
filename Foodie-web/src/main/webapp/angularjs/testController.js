/**
 * 
 */
app.controller("testController", function($scope) {
	$scope.firstName = "John";
	$scope.lastName = "Doe";
});

app.controller("httpController", function($scope, $http) {
//	var dish = {
//		name : 1
//	};
	$http({
		method : 'GET',
		url : "ws/dish/searchdish"
	}).success(function(data) {
		if (!data.errorCode) {
			$scope.dish = data.dish;
//			console.log(dish);
			
//			if (dish.length > 0) {
//				for (i = 0; i < dish.length; i++) {
//					$scope.id = dish[i].id;
//					$scope.name = dish[i].name;
//					$scope.price = dish[i].price;
//					$scope.restId = dish[i].restID;
//					$scope.categoryId = dish[i].categoryId;
//				}
//			}
//			$scope.res3 = data;
		}
	})
});