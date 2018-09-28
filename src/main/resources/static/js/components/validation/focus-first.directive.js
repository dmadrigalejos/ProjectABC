(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('focusFirst', focusFirst);

    focusFirst.$inject = [];

    function focusFirst() {
        var directive = {
            restrict: 'AC',
            require: 'form',
            link: linkFunc
        };

        return directive;

        function linkFunc($scope, $element, $attributes, $form) {
            $form.$focusFirstError = focusFirstError;

            function focusFirstError() {
                $element.find('.ng-invalid:first').focus();
            }
        }
    }

})();