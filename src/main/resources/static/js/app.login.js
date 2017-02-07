angular.module( 'store.login', [
  'ui.router',
  'angular-storage'
])
app.config(function($stateProvider) {
  console.log('login config');
  

  $stateProvider.state('login', {
	    url: '/login',
	    controller: 'LoginCtrl',
	    templateUrl: 'ui/login.html',
	    data: {
	    	'requiresLogin': false
	    }
  });
})

app.controller('LoginCtrl', function($scope, $http, $location, store, $state, jwtHelper, Restangular, loggedUserService){
    console.log('login controller start');
	$http.defaults.headers.post["Content-Type"] = "application/json";
	
	// get data for Store dropdown 
    $scope.selectedStore = null;
    $scope.stores = [];
    $http({
    	method: 'GET',
        url: '/stores'
    }).success(function (result) {
        $scope.stores = result;
    });
  
    $scope.processLogin = function() {
        var jobs = Restangular.all('login');
        jobs.post($scope.auth).then(function(response){
            console.log('Succeeded login attempt.');
            store.set('jwt', response.token);
                
            var tokenPayload = jwtHelper.decodeToken(response.token);
            console.log(tokenPayload);
            console.log("Token Username:"+tokenPayload.sub);
            console.log("Token User ID:"+tokenPayload.user_id);
            console.log("Token StoreId:"+tokenPayload.store);
                
            loggedUserService.prepForBroadcast(tokenPayload.sub, tokenPayload.store_name);
            $state.go('locations', { 'userId':tokenPayload.user_id, 'storeId':tokenPayload.store });
        }, function(response) {
            console.log('Wrong credentials.');
    	    $scope.errors = {};
    	    var modelState = {password: "Bad credentials."};
    	    errorHelper.validateForms($scope.loginForm, $scope.errors, modelState);
        });
    }
});
