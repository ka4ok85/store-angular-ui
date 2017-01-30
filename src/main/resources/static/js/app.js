angular.module('initFromForm', [])
  .directive("initFromForm", function ($parse) {
    return {
      link: function (scope, element, attrs) {
        var attr = attrs.initFromForm || attrs.ngModel || element.attrs('name'),
        val = attrs.value;
        $parse(attr).assign(scope, val)
      }
    };
});

var app = angular.module('storeApp', [
                                          'ngRoute', 
                                          'ngCacheBuster',
                                          'initFromForm', 
                                          'restangular',
                                          'ui.router',
                                          'store.login',
                                          'store.product',
                                          'angular-jwt',
                                          'angular-storage'
                                          ]
);

app.factory('loggedUserService', function($rootScope) {
    var sharedService = {};
    
    sharedService.login = "";
    sharedService.store = "";
    sharedService.isLogged = "0";

    sharedService.prepForBroadcast = function(login, store) {
        this.login = login;
        this.store = store;
        if (login != "" && store != "") {
        	this.isLogged = "1";
        } else {
        	this.isLogged = "0";
        }
        
        this.broadcastItem();
    };

    sharedService.broadcastItem = function() {
        $rootScope.$broadcast('handleBroadcast');
    };

    return sharedService;
});


app.config( function myAppConfig ($urlRouterProvider, $routeProvider, jwtInterceptorProvider, $httpProvider, $stateProvider, httpRequestInterceptorCacheBusterProvider) {
    console.log('app config start');  

    httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*ui.*/],true);

    jwtInterceptorProvider.tokenGetter = function(store) {
		console.log("stored token " + store.get('jwt'));
	    return store.get('jwt');
	}

	$stateProvider.state('restatement_jobs', {
		    url: '/ui/restatement_jobs',
		    cache: false,
		    controller: 'ListCtrl',
		    templateUrl:function ($stateParams){
		        return 'ui/restatement_jobs' + '.html';
		    },
		    params: {
		        'userId': null, 
		        'storeId': null
		    }
	});  

	$stateProvider.state('restatement_job_add', {
		    url: '/ui/add_restatement_job',
		    controller: 'AddCtrl',

		    templateUrl:function ($stateParams){
		        return 'ui/add_restatement_job' + '.html';
		    },		    
		    params: {
		        'userId': null, 
		        'storeId': null
		    }
	});

	$stateProvider.state('restatement_job_view', {
		    url: '/ui/view_restatement_job',
		    controller: 'ViewCtrl',
		    //templateUrl: 'ui/view_restatement_job.html',
		    templateUrl:function ($stateParams){
		        return 'ui/view_restatement_job/' + $stateParams.jobId + '.html';
		    },		    
		    params: {
		        'jobId': null
		    }
	});

	$httpProvider.interceptors.push('jwtInterceptor');
})


app.run(function($rootScope, $state, store, jwtHelper) {
  console.log('app run start');
  if (!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))) {
	  console.log('jwt does not exist or expired');
	  // force login
    $state.go('login');
  } else {
    // redirect to dashboard
    $state.go('products');
  }
	
  /*
  $rootScope.$on('$stateChangeStart', function(e, to) {
	console.log('stateChangeStart');
    if (to.data && to.data.requiresLogin) {
      console.log('to.data && to.data.requiresLogin - passed');
      if (!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))) {
        console.log('stateChangeStart - force login');
        e.preventDefault();
        $state.go('login');
      }
    }
  });
  */
})

app.controller( 'AppCtrl', function AppCtrl ($scope, $location, $state, store, loggedUserService ) {
  $scope.loggedUser = "";
  $scope.currentStore = "";
  $scope.isLogged = "0";
  $scope.userRoles = null;


  $scope.$on('handleBroadcast', function() {
	$scope.loggedUser = loggedUserService.login;
    $scope.currentStore = loggedUserService.store;
    $scope.isLogged = loggedUserService.isLogged;
  }); 

  $scope.$on('$routeChangeSuccess', function(e, nextRoute){
	console.log(nextRoute);
  });

  $scope.logout = function() {
	console.log('log out.');
	store.remove('jwt');
	loggedUserService.prepForBroadcast("", "");
	$state.go('login');
  };

// Main menu links
  $scope.callFunction = function (name) {
    if(angular.isFunction($scope[name])) {
  	  $scope[name](); 
    }
  }

  $scope.navmenu = [];
  var products = {title: "Products", clickFunction: "products"};
  $scope.navmenu.push(products);
  var locations = {title: "Locations", clickFunction: "locations"};
  $scope.navmenu.push(locations);
  var restatementJobs = {title: "Restatement Jobs", clickFunction: "restatementjobs"};
  $scope.navmenu.push(restatementJobs);  


  $scope.selectedIndex = 0;
  $scope.itemClicked = function ($index) {
    $scope.selectedIndex = $index;
  }

  $scope.products = function() {
	$state.go('products');
  };
  
  $scope.locations = function() {
		$state.go('locations');
  };
  
  $scope.restatementjobs = function() {
		$state.go('restatement_jobs');
  };

})