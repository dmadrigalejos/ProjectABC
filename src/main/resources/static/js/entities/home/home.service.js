(function () {
    'use strict';

    angular
        .module('ABC')
        .factory('HomeService', HomeService);

    HomeService.$inject = ['$http'];

    function HomeService($http) {
        var factory = {

        };

        return factory;
        
    }
})();