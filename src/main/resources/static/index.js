angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'get',
            params: {
                min: $scope.filter ? $scope.filter.min : null,
                max: $scope.filter ? $scope.filter.max : null,
                title: $scope.title ? $scope.filter.title : null,
                page: $scope.page ? $scope.filter.page : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/products/cart',
            method: 'get',
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.post(contextPath + '/products/cart', productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.removeFromCart = function (productId) {
        $http.delete(contextPath + '/products/cart/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8189/app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    $scope.loadProducts();
});