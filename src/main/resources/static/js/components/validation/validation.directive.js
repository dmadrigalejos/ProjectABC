(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('validation', validation);

    function validation() {

        var directive = {
            require: 'ngModel',
            link: linkFunc
        };

        return directive;

        function linkFunc($scope, $element, $attributes, $ngModel) {
            var codes = $attributes.validation.trim().split(new RegExp('\\s*,\\s*'));
            var ngModelWatcher = $scope.$watch($attributes.ngModel, function (value) {
                if ($ngModel.$invalid) {
                    angular.forEach(codes, function (code) {

                        $ngModel.$setValidity(code, true);

                    })
                }
            });
            var validationErrorsWatcher = $scope.$watch($attributes.validationErrors, function (errors) {
                angular.forEach(errors, function (value) {
                    var field = value.field;
                    var code = value.code;
                    var message = value.defaultMessage;

                    if (field === $ngModel.$name) {
                        $ngModel.$setValidity(code, false);
                        $ngModel.$error[code] = message;
                    }
                });
            });

            $scope.$on('$destroy', function() {
                ngModelWatcher();
                validationErrorsWatcher();
            });
        }

    }

})();