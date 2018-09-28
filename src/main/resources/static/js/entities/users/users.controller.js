(function () {
    'use strict';

    angular
        .module('ABC')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$rootScope', '$stateParams', '$state', 'UsersService', '$uibModal'];

    function UsersController($rootScope, $stateParams, $state, UsersService, $uibModal) {
        var vm = this;
        vm.users = [];
        
        vm.itemsPerPageOption = [10, 25, 50 , 100];
        vm.pagination = {
                maxSize: 5,
                itemsPerPage: vm.itemsPerPageOption[0],
                users: {
                    totalItems: vm.users.length > 0 ? vm.users.length : 0,
                    searchQuery: '',
                    pageNumber: 1,
                    sortBy: 'username',
                    order: 'ASC'
                }
            };
        
        // public functions
        vm.search = search;
        vm.sort = sort;
        vm.keySearch = keySearch;
        vm.deleteUser = deleteUser;
        
        search();
        
        function search() {
        	UsersService.getUsers(vm.pagination.users.searchQuery, vm.pagination.users.pageNumber, vm.pagination.users.sortBy, vm.pagination.users.order, vm.pagination.itemsPerPage)
        	.then(function(result){
        		vm.users = result.data.Sites;
        		
        		vm.pagination = {
                    maxSize: 5,
                    itemsPerPage: vm.pagination.itemsPerPage,
                    users: {
                        totalItems: vm.users ? result.data.TotalRows : 0,
                        searchQuery: vm.pagination.users.searchQuery,
                        pageNumber: vm.pagination.users.pageNumber,
                        sortBy: vm.pagination.users.sortBy,
                        order: vm.pagination.users.order
                    }
                };
        	})
        	.catch(function(error){
        		console.log(error);
        	});
        }
        
        function keySearch(event) {
            if (event.keyCode == 13) {
                search();
            }
        }
        
        function sort(sortBy) {
        	vm.pagination.users.sortBy = sortBy;
        	vm.pagination.users.order = vm.pagination.users.order === 'ASC' ? 'DESC' : 'ASC';
        	search();
        }
        
        function deleteUser(userId) {
        	var content = {
                    title: "Delete",
                    body: 'Are you sure you want to delete user? WARNING: This action cannot be reverted.'
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
                    	UsersService.deleteUser(userId)
                    	.then(function(result){
                    		search();
                    	})
                    	.catch(function(error){
                    		console.log(error);
                    	})
                    }
                })
        }
    }
})();