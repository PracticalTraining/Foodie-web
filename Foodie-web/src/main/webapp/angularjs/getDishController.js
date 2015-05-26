/**
 * 
 */
app.controller("getController", function($scope, $http) {
	$scope.dishId = {};
	// var dish = {
	// name : 1
	// };
	$http({
		method : 'GET',
		url : "ws/dish/searchdish"
	}).success(function(data) {
		if (!data.errorCode) {
			$scope.dish = data.dish;
			$scope.NumberOfDish = data.dish.length;
			// console.log(dish);

			// if (dish.length > 0) {
			// for (i = 0; i < dish.length; i++) {
			// $scope.id = dish[i].id;
			// $scope.name = dish[i].name;
			// $scope.price = dish[i].price;
			// $scope.restId = dish[i].restID;
			// $scope.categoryId = dish[i].categoryId;
			// }
			// }
			// $scope.res3 = data;
		}
	})

	$scope.remove = function(index, id) {
		$scope.dish.splice(index, 1);
		console.log("index: " + index);
		console.log("id: " + id);
		$http({
			method : 'DELETE',
			// url : 'ws/dish/{' + id + '}'
			url : 'ws/dish/' + id
		// pass in data as strings
		// set the headers so angular passing info as form data (not request
		// payload)
		}).success(function(data) {
			console.log(data);
			if (data.success) {
				$scope.message = data.message;
			}
		});
	};
	
});