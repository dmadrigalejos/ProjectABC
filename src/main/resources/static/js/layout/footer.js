(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('foot', footer);

    footer.$inject = [];

    function footer() {
        var directive = {
            restrict: 'E',
            templateUrl: 'js/layout/footer.html',
            replace: true
        };

        return directive;
    }
})();