(function () {
    'use strict';

    angular
        .module('ABC')
        .directive('alertMessage', alertMessage);

    alertMessage.$inject = ['$rootScope', '$timeout'];

    function alertMessage($rootScope, $timeout) {
        var directive = {
            restrict: 'E',
            replace: true,
            templateUrl: 'js/components/alert/alert.html',
            link: linkFunc
        };

        return directive;

        function linkFunc($scope) {
            var alertTimer;
            $scope.show = false;

            $rootScope.$on('alert-message', function (events, args) {
                $rootScope.$broadcast('alert-message-close');
                $timeout(function () {
                    $scope.alertType = args.alertType || 'success';
                    $scope.isDismissible = args.isDismissible || true;
                    $scope.timer = args.timer || 10000;
                    $scope.title = args.title;
                    $scope.message = args.message;
                    $scope.show = true;
                    $scope.animation = true;
                    $rootScope.$broadcast('alert-message-shown');

                    if ($scope.timer > 0) {
                        alertTimer = $timeout(function () {
                            $scope.closeAlert();
                        }, $scope.timer);
                    }
                }, 200);

            });

            $rootScope.$on('alert-message-hide', function () {
                $scope.show = false;
            });

            $rootScope.$on('alert-message-close', function () {
                if (alertTimer)
                    $timeout.cancel(alertTimer);

                $scope.closeAlert();
            });

            $scope.closeAlert = function () {
                $scope.show = false;
                $timeout(function () {
                    $scope.title = '';
                    $scope.message = '';
                }, 200);

                $rootScope.$broadcast('alert-message-closed');
            }
        }

    }

})();