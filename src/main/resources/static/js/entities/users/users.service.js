(function () {
    'use strict';

    angular
        .module('ABC')
        .factory('UsersService', UsersService);

    UsersService.$inject = ['$http'];

    function UsersService($http) {

        var factory = {
        		getUsers: getUsers,
        		getUser: getUser,
        		editUser: editUser,
        		deleteUser: deleteUser,
        		newUser: newUser,
        		getActiveUsers: getActiveUsers
        };

        return factory;
        
        
        function getUsers(searchQuery, pageNumber, sortBy, order, limit) {
        	return $http({
                method: 'POST',
                url: 'user/getusers',
                params: {
                	searchQuery: searchQuery,
                	pageNumber: pageNumber,
                	sortBy: sortBy,
                	order: order,
                	limit: limit	
                }
            })
        }
        
        function deleteUser(userId) {
            return $http({
                method: 'POST',
                url: 'user/delete/' + encodeURIComponent(userId)                
            })
        }
        
        function newUser(user) {
        	return $http({
        		method: 'POST',
        		url: 'user/',
        		data: user
        	})
        }
        
        function getUser(id) {
        	return $http({
        		method: 'GET',
        		url: 'user/' + id
        	})
        }
        
        function editUser(user) {
        	return $http({
        		method: 'PATCH',
        		url: 'user/',
        		data: user
        	})
        }
        
        function getActiveUsers() {
        	return $http({
        		method: 'GET',
        		url: 'user/active'
        	})
        }
    }
})();