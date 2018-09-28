(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('LogoutController', LogoutController);

    LogoutController.$inject = ['$rootScope', '$state', 'AuthenticationService'];

    function LogoutController($rootScope, $state, AuthenticationService) {
        var vm = this;

        AuthenticationService.invalidate()
        .then(function(result){
            $rootScope.user = undefined;
            $state.go('login');
        })
        .catch(function(error){
        });
    }
})();
