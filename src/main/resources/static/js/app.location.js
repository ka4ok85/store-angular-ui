angular.module( 'store.location', [
  'ui.router',
  'ui.bootstrap',
  'angular-storage'
])

app.config(function($stateProvider) {
  
	$stateProvider.state('locations', {
	    url: '/ui/locations',
	    cache: false,
	    controller: 'LocationsListCtrl',
	    templateUrl:function ($stateParams){
	        return 'ui/locations' + '.html';
	    },
	    data: {
	    	'requiresLogin': true
	    }
    });
	
	$stateProvider.state('location', {
	    url: '/ui/location',
	    cache: false,
	    controller: 'LocationViewCtrl',
	    templateUrl:function ($stateParams){
	        return 'location.html?locationId=' + $stateParams.locationId;
	    },
	    params: {
	        'locationId': null
	    },
	    data: {
	    	'requiresLogin': true
	    }
    }); 
})


app.controller('LocationsListCtrl', function($scope, $http, $location, $state, $stateParams) {

	$scope.viewLocation = function(id) {
        $state.go('location', {'locationId':id });
    }
    
    var app = this;
    app.currentPage = 1;
    app.maxSize = 10;
    app.itemPerPage = 10;
    app.totalItems = 0;

    app.getPagableRecords = function() {
        var param = {
                page : app.currentPage,
                size : app.itemPerPage  
        };

        $http.get("/locations", {params : param})
        .success(function(data,status,headers,config){
            app.locations = data.content;
            app.totalItems = data.totalElements;
            console.log(data);
        })
        .error(function(data,status,header,config){
            console.log(data);
        });
    };

    app.getPagableRecords();
});


app.controller('LocationViewCtrl', function($scope, $http, $location, $state, $stateParams) {

	$scope.viewLocation = function(id) {
        $state.go('location', {'locationId':id });
    }
    
    var app = this;
/*
    app.currentPage = 1;
    app.maxSize = 10;
    app.itemPerPage = 10;
    app.totalItems = 0;

    app.getPagableRecords = function() {
        var param = {
                page : app.currentPage,
                size : app.itemPerPage  
        };

        $http.get("/locations", {params : param})
        .success(function(data,status,headers,config){
            app.locations = data.content;
            app.totalItems = data.totalElements;
        })
        .error(function(data,status,header,config){
            console.log(data);
        });
    };

    app.getPagableRecords();
    */
});