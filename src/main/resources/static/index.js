angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

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

    $scope.loadProducts();
});