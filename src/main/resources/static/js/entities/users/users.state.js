(function () {
    'use strict';

    angular
        .module("ABC")
        .config(stateConfig)

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	
        $stateProvider
        .state('dashboard.users',{
            url: '/user',
            controller: 'UsersController',
            controllerAs: 'vm',
            templateUrl: 'js/entities/users/users.html'
        })
        .state('dashboard.usersnew',{
            url: '/user/new',
            controller: 'NewUsersController',
            controllerAs: 'vm',
            templateUrl: 'js/entities/users/users-new.html'
        })
        .state('dashboard.useredit',{
            url: '/users/:id',
            controller: 'EditUsersController',
            controllerAs: 'vm',
            templateUrl: 'js/entities/users/users-edit.html',
            resolve: {
            	UserDetails: ['UsersService', '$stateParams',
            		function(UsersService, $stateParams){
            			return UsersService.getUser($stateParams.id)
            		}]
            }
        })
    }
})();



