angular.module('EchoWebAppModule').controller('loginController', function($scope, $http, UserService, EchoService) {
	$scope.title="Login page:"

	// login function
	$scope.login = function (username, pwd) {
		console.log('user: ' + username + ', pwd=' + pwd);
		 var dataObj = {
			username : username,
			password : pwd
		 };
		 $http.post("servlet/login", dataObj)
			.then(function(rsp){
				console.log("rsp: " + rsp.data.username + ":" + rsp.data.loginStatus + ":" + rsp.data.statusMsg);
				//$scope.users = success.data;
				if(rsp.data.loginStatus) {
					$scope.title= rsp.data.username + " login sucessfully!" ;
				} else {
					$scope.title= rsp.data.username + " failed to login. " + rsp.data.statusMsg;
				}
				console.log($scope.title);
			}, function (error) {
					// an error here means there was some type of server communication error. Don't try to respond to a 401
					// here since the authIntercept will do that
					console.log('[auth] login attempt failed because ' + error.status + ' ' + error.statusText);
			});
	};

});
