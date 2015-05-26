/**
 * 
 */
app.controller("postController", function($scope, $http) {
	$scope.formData = {};
	$scope.processForm = function() {
		$http({
			method : 'POST',
			url : 'ws/dish',
			data : $.param($scope.formData) // pass in data as strings
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