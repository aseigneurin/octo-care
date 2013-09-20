angular.module("octoCare", ["ui.bootstrap"])
    .config(["$routeProvider", function($routeProvider) {
          $routeProvider.
              when("", {templateUrl: "partials/octo-list.html",   controller: OctoListCtrl}).
              when("/octo/:octoId", {templateUrl: "partials/octo-detail.html", controller: OctoDetailCtrl}).
              otherwise({redirectTo: ""});
}]);

var OctoListCtrl = function($scope) {
    $scope.octos = octos;

    $scope.openDetail = function(octo) {
        window.location = "#/octo/" + octo.id;
    }
}

var OctoDetailCtrl = function($scope, $routeParams, $modal) {
    $scope.octoId = $routeParams.octoId;
    $scope.octo = octoDetails[$scope.octoId];
    $scope.history = $scope.octo.history;

    $scope.createEvent = function() {
        var modalInstance = $modal.open({
            templateUrl: "partials/new-event.html",
            controller: NewEventCtrl
        });

        modalInstance.result.then(function(result) {
            $scope.history.push({ date: new Date(), text: result.text, highlight: "new" });
        });
    }
}

var NewEventCtrl = function($scope, $modalInstance) {
    $scope.validate = function() {
        $modalInstance.close(this);
    }
    $scope.cancel = function() {
        $modalInstance.dismiss("cancel");
    }
}
