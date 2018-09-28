(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', 'AuthenticationService'];

    function LoginController($rootScope, $state, AuthenticationService) {
        var vm = this;
        vm.login = login;

        function login(isValid){
            if (!isValid) 
                return;

            AuthenticationService.login(vm.user)
            .then(function(result){
                $rootScope.user = result.data;
                $state.go('dashboard.home');
            })
            .catch(function(error){
                if (error.data != null) {
                    vm.status = error.data.userName;
                }
            });
        }
    }
})();