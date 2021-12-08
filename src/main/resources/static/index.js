angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'get',
            params: {
                min: $scope.filter ? $scope.filter.min : null,
                max: $scope.filter ? $scope.filter.max : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.deleteProduct = function (studentId) {
        $http.get(contextPath + '/products/delete/' + studentId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeScore = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_score',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.addProductJson = function () {
        console.log($scope.ProductJson);
        $http.post(contextPath + '/products', $scope.ProductJson)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.applyFilters = function () {
        console.log($scope.calcAdd);
        $http({
            url: contextPath + '/calc/add',
            method: 'get',
            params: {
                a: $scope.calcAdd.a,
                b: $scope.calcAdd.b
            }

        }).then(function (response) {
            alert('Сумма равна ' + response.data.value);
            $scope.calcAdd.a = 10000;
        });
    }

    $scope.loadProducts();
});