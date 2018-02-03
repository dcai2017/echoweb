//angular.module("EchoWebAppModule", ["ngRoute", "ngAnimate", "ngSanitize", "mgcrea.ngStrap", "datatables"])
var app=angular.module("EchoWebAppModule", ["ngRoute", "ngAnimate", "ngSanitize", "mgcrea.ngStrap", "datatables"]);
	// SCAI: add this line to remove the #! in URL which was introduced in Angular 1.6
	app.config(['$locationProvider', function($locationProvider) {
  		$locationProvider.hashPrefix('');
	}]);

	app.config(['$routeProvider', function($routeProvider){
		$routeProvider
			.when("/", {
						templateUrl: "views/home.html",
						controller: "homeController"
			})
			.when("/home", {
				templateUrl: "views/home.html",
				controller: "homeController"
			})
			.when("/employee", {
				templateUrl: "views/employee.html",
				controller: "employeeController"
			})
			.when("/grids", {
				templateUrl: "views/grids.html",
				controller: "gridsController"
			})
			.when("/form", {
				templateUrl: "views/form.html",
				controller: "formController"
			})
			.when("/login", {
				templateUrl: "views/login.html",
				controller: "loginController"
			})
			.otherwise ({
				redirectTo: "/home"
			});
	}]);
app.controller("homeController", function($scope){
	$scope.title="I'm in home"
});

