(function () {
    'use strict';

    angular
        .module("ABC", ['ui.router','ui.router.state.events','ui.bootstrap','ngMessages', 'angular.filter'])
        .config(stateConfig)
        .run(stateRun)

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider','$httpProvider'];
    stateRun.$inject = ['$rootScope', 'alertService', 'AuthenticationService', '$state'];

    function stateConfig($stateProvider,$urlRouterProvider,$httpProvider) {

        $urlRouterProvider.otherwise('login');

        $stateProvider
        .state('dashboard', {
            url:'/dashboard',
            templateUrl: 'js/app.html'
        })
        .state('dashboard.home',{
            url:'/home',
            controller: 'HomeCtrl',
            templateUrl:'js/entities/home/home.html',
            controllerAs: 'vm'
        })
        .state('login',{
            templateUrl:'js/entities/login/login.html',
            url:'/login',
            controller: 'LoginController',
            controllerAs: 'vm'
        })
        .state('dashboard.logout',{
            url: '/logout',
            controller: 'LogoutController',
            controllerAs: 'vm'
        })
        
        $httpProvider.interceptors.push('notificationInterceptor');
    }

    function stateRun($rootScope, alertService, AuthenticationService, $state, Idle) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.nextPage = toState.name;
            $rootScope.prevPage = fromState.name;
            
            if ($rootScope.user === undefined || $rootScope.user.userName === "") {
	            AuthenticationService.authenticate()
	            .then(function(result){
	           	 
	            	$rootScope.user = result.data;
	           	
	            	if (result.data.userName == '') {
	            		$state.go('login');
	            	}else if ($rootScope.prevPage == 'login') {
	                   $state.go('dashboard.home');
	            	}
            	})
	            .catch(function(error){
	               $state.go('login');
	            });
            }
             return;
        });
    }
})();