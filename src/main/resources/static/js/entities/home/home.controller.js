(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['$filter', '$rootScope', 'AuthenticationService', 'HomeService'];

    function HomeCtrl($filter, $rootScope,  AuthenticationService, HomeService) {
        var vm = this;
    }
})();