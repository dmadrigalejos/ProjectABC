(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('NewUsersController', NewUsersController);

    NewUsersController.$inject = ['$state', '$uibModal', 'UsersService'];

    function NewUsersController($state, $uibModal, UsersService) {
        var vm = this;
        
        // variables
        vm.form = {};
        vm.user = {};
        vm.formErrors = [];
        
        // public functions
        vm.newUser = newUser;
        vm.reset = reset;

        function newUser() {
        	if (vm.form.$valid) {
        		var content = {
                    title: "New User",
                    body: 'Are you sure you want to add new user?'
                };
                var modalInstance = $uibModal.open({
                    controller: 'ConfirmationController',
                    controllerAs: 'vmConfirmation',
                    templateUrl: 'js/components/confirmation/confirmation.html',
                    size: 'md',
                    backdrop: 'static',
                    keyboard: false,
                    resolve: {
                        content: content
                    }
                })

                modalInstance.result.then(function (response) {
                    if (response) {
                    	// submit here
                    	UsersService.newUser(vm.user)
                		.then(function(result){
                			$state.go('dashboard.users');
                		})
                		.catch(function(error){
                			vm.form.$setDirty();
                            vm.formErrors = error.data.errors;
                		})
                    }
                })
        	} else {
        		vm.form.$setDirty();
        	}
        }
        
        function reset() {
        	vm.user = {};
        	vm.form.$setPristine();
        }
    }
})();