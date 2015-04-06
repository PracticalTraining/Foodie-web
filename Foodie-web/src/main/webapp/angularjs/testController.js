/**
 * 
 */
app.controller("testController", function($scope) {
	$scope.firstName = "John";
	$scope.lastName = "Doe";
});

app.controller("httpController", function($scope, $http) {
	var dish = {
		name : 1
	};
	$http({
		method : 'GET',
		url : "ws/dish/search",
		params : dish
	}).success(function(data) {
		console.log(data);
		$scope.res1 = data;
		if (!data.errorCode) {
			var dish=data.dish;
			if(dish.length>0){
				for(i=0;i<dish.length;i++){
					$scope.res2=dish[i].name;
				}
			}
			$scope.res3 = data;
		}
	})
});