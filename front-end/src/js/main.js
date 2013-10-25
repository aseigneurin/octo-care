angular.module("octoCare", ["ui.bootstrap", "angulartics", "angulartics.google.analytics"])
    .config(["$routeProvider",
        function($routeProvider) {
            $routeProvider.
            when("", {
                templateUrl: "partials/authentication.html",
                controller: AuthenticationCtrl
            }).
            when("/list", {
                templateUrl: "partials/octo-list.html",
                controller: OctoListCtrl
            }).
            when("/octo/:octoId", {
                templateUrl: "partials/octo-detail.html",
                controller: OctoDetailCtrl
            }).
            otherwise({
                redirectTo: ""
            });
        }
    ]);

var AuthenticationCtrl = function($scope, $http) {

    $http.get("api/hexa/current").success(function(data) {
        $scope.hexa = data;
        if ($scope.hexa.id !== undefined) {
            window.location = "#/list";
        }
    }).error(function(data, status, headers, config) {
        alert("Failed loading the current Hexa...");
    });
};

var OctoListCtrl = function($scope, $http) {

    $http.get("api/octo").success(function(data) {
        $scope.octos = data;
    }).error(function(data, status, headers, config) {
        alert("Failed loading the Octos...");
    });

    $scope.openDetail = function(octo) {
        window.location = "#/octo/" + octo.id;
    };
};

var OctoDetailCtrl = function($scope, $routeParams, $http, $modal) {
    $scope.octoId = $routeParams.octoId;

    $http.get("api/octo/" + $scope.octoId).success(function(data) {
        $scope.octo = data;
    }).error(function(data, status, headers, config) {
        alert("Failed loading the Octo...");
    });

    $http.get("api/octo/" + $scope.octoId + "/history").success(function(data) {
        $scope.history = data;
    }).error(function(data, status, headers, config) {
        alert("Failed loading the history...");
    });

    $scope.createEvent = function() {
        var modalInstance = $modal.open({
            templateUrl: "partials/new-event.html",
            controller: NewEventCtrl
        });

        modalInstance.result.then(function(result) {
            var data = {
                text: result.text
            };
            $http.post("api/octo/" + $scope.octoId + "/history", data).success(function(data) {
                data.highlight = "new";
                console.log(data);
                $scope.history.push(data);
            }).error(function(data, status, headers, config) {
                alert("Failed saving the event...");
            });
            /*$scope.history.push({
                date: new Date(),
                text: result.text,
                highlight: "new"
            });*/
        });
    };
};

var NewEventCtrl = function($scope, $modalInstance) {
    $scope.validate = function() {
        $modalInstance.close(this);
    };
    $scope.cancel = function() {
        $modalInstance.dismiss("cancel");
    };
};