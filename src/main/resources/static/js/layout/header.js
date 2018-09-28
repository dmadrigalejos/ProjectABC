(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('header', header);

    header.$inject = [];

    function header() {
        var directive = {
            restrict: 'E',
            templateUrl: 'js/layout/header.html',
            replace: true
        };

        return directive;
    }
})();