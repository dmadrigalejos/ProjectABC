(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('EditUsersController', EditUsersController);

    EditUsersController.$inject = ['$state', '$uibModal', 'UsersService', 'UserDetails'];

    function EditUsersController($state, $uibModal, UsersService, UserDetails) {
        var vm = this;
        
        // variables
        vm.form = {};
        vm.user = {};
        vm.formErrors = [];
        
        // initialize
        angular.copy(UserDetails.data, vm.user);
        
        // public functions
        vm.editUser = editUser;
        vm.reset = reset;

        function editUser() {
            if (vm.form.$valid) {
                var content = {
                    title: "Edit",
                    body: 'Are you sure you want to edit user?'
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
                        UsersService.editUser(vm.user)
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
            angular.copy(UserDetails.data, vm.user);
            vm.form.$setPristine();
        }
    }
})();