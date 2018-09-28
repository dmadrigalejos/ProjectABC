(function () {
    'use strict';

    angular
        .module('ABC')
        .factory('notificationInterceptor', notificationInterceptor);

    notificationInterceptor.$inject = ['$q', 'alertService', '$state'];

    function notificationInterceptor($q, alertService, $state) {

        var factory = {
            response: response,
            responseError: responseError
        };

        return factory;

        function response(response) {
        	
            var alertMessage = response.headers("X-Alert-Message"),
                alertType = response.headers("X-Alert-Type");

            if (angular.isString(alertMessage)) {
            	alertService.open({
            		alertType: alertType,
            		message: alertMessage,
                    timer: 5000
                })
            }

            return response;

        }

        function responseError(error) {
            var alertMessage = error.headers("X-Alert-Message"),
                alertType = error.headers("X-Alert-Type");

            if (angular.isString(alertMessage)) {
            	alertService.open({
                    alertType: alertType,
                    message: alertMessage,
                    timer: 5000
                })
            }

            return $q.reject(error);

        }

    }

})();