(function () {
    'use strict';

    angular
        .module('ABC')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http'];

    function AuthenticationService($http) {
        var factory = {
    		login: login,
            authenticate: authenticate,
            invalidate: invalidate
        };

        return factory;
        
        function login(user) {
            return $http({
                method: 'POST',
                url: 'login',
                data: user
            })
        }

        function authenticate() {
            return $http({
                method: 'GET',
                url: 'login/authenticate'
            });
        }

        function invalidate() {
            return $http({
                method: 'GET',
                url: 'login/invalidate'
            });
        }
    }
})();