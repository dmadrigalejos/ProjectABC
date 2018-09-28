(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('pagination', pagination);

    function pagination() {

        var directive = {
            restrict: 'E',
            scope: {
                ngModel: '=',
                itemsPerPage: '=',
                maxSize: '=',
                totalItems: '=',
                ngChange: '&',
                pageSize: '='
            },
            templateUrl: 'js/components/pagination/pagination.html',
            link: linkFunc
        };

        return directive;

        function linkFunc($scope) {
            var modelWatcher = $scope.$watch('ngModel', function (value) {
                if (value !== undefined)
                    $scope.ngChange();
            });
            $scope.$on('$destroy', function() {
               modelWatcher();
            })
        }

    }

})();