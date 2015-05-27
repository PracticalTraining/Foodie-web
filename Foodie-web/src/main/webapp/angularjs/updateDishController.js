///**
// * 
// */
//app.controller("updateController", function($scope, $http) {
//
//	$scope.direct = function(name) {
//		console.log("name: " + name);
//		$http({
//			method : 'GET',
//			url : "ws/dish/search?name=" + name
//		}).success(function(data) {
//			console.log(data);
//			if (!data.errorCode) {
//				$scope.dish = data.dish;
//				$scope.NumberOfDish = data.dish.length;
//			}
//		})
//	};
//
//});