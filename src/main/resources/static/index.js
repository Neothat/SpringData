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

    // $scope.deleteProduct = function (studentId) {
    //     $http.delete(contextPath + '/products/' + studentId)
    //         .then(function (response) {
    //             $scope.loadProducts();
    //         });
    // }
    //
    // $scope.changeScore = function (productId, delta) {
    //     $http({
    //         url: contextPath + '/products/change_score',
    //         method: 'GET',
    //         params: {
    //             productId: productId,
    //             delta: delta
    //         }
    //     }).then(function (response) {
    //         $scope.loadProducts();
    //     });
    // }
    //
    // $scope.addProductJson = function () {
    //     console.log($scope.ProductJson);
    //     $http.post(contextPath + '/products', $scope.ProductJson)
    //         .then(function (response) {
    //             $scope.loadProducts();
    //         });
    // }

    $scope.loadProducts();
});