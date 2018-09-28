(function () {
    'use strict';

    angular
        .module('ABC')
        .factory('alertService', alertService);

    alertService.$inject = ['$rootScope'];

    function alertService($rootScope) {

        var factory = {
            closeAlert: closeAlert,
            open: open,
            success: success,
            info: info,
            warning: warning,
            error: error
        };

        return factory;


        function closeAlert() {
            $rootScope.$broadcast('alert-message-close');
        }

        function open(options) {
            showAlertMessage(options);
        }

        function success(options) {
            options.alertType = 'success';
            showAlertMessage(options);
        }

        function info(options) {
            options.alertType = 'info';
            showAlertMessage(options);
        }

        function warning(options) {
            options.alertType = 'warning';
            showAlertMessage(options);
        }

        function error(options) {
            options.alertType = 'error';
            showAlertMessage(options);
        }

        function showAlertMessage(options) {
            $rootScope.$broadcast('alert-message', {
                alertType: options.alertType,
                isDismissible: options.isDismissible,
                timer: options.timer,
                title: options.title,
                message: options.message
            })
        }
    }

})();