/**
 * Created by reloaded on 21.8.2016.
 */

var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http) {


    $scope.refreshCart = function (cartId) {
        // function to refresh the cart
        $http.get('/rest/cart/'+$scope.cartId).success(function (data) {
            $scope.cart = data;
        });
        // pass the cartId,
        // in backend java -> retrieve path variable, use cartdao to read cartid, returns a cart
        // this javascript method gets the cart data, when it successfully gets it,
        // cart information will be stored in JSON format
    };

    $scope.clearCart = function (cartId) {

        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");

        $.ajax({
            url: '/rest/cart/'+$scope.cartId,
            type: 'DELETE',
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(data) {
                console.log(data);
                $scope.refreshCart($scope.cartId)
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status + ": " + thrownError);
            }
        });

        //$http.delete('/webapp/rest/cart/'+$scope.cartId).success($scope.refreshCart($scope.cartId));
    };

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId) {

        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");

        $.ajax({
            url: '/rest/cart/add/'+ productId,
            type: 'PUT',
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(data) {
                console.log(data);
                $scope.refreshCart($http.get('/rest/cart/get/' + $scope.cartId));
                alert("Product successfully added to the cart!");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status + ": " + thrownError);
            }
        });

        //$http.put('/webapp/rest/cart/add/' + productId).success(function (data) {
        //    $scope.refreshCart($http.get('/webapp/rest/cart/get/cartId'));
        //    alert("Product successfully added to the cart!");
        //});
    };

    $scope.removeFromCart = function (productId) {

        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");

        $.ajax({
            url: '/rest/cart/remove/' + productId,
            type: 'PUT',
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(data) {
                console.log(data);
                $scope.refreshCart($http.get('/rest/cart/get/'+$scope.cartId));
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status + ": " + thrownError);
            }
        });

        //$http.put('/webapp/rest/cart/remove/' + productId).success(function (data) {
        //    $scope.refreshCart($http.get('/webapp/rest/cart/get/cartId'));
        //});
    };
});