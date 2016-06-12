/**
 * Created by mateusz on 04.03.16.
 */

    //var mlApp = angular.module('myApp');
angular.module('myApp').controller('executionsListCtrl',function ($scope,$location,$routeParams, Algorithm_execution, Method_configuration, Problem_configuration) {
    $scope.executions = [];
    $scope.method_configurations = [];
    $scope.problem_configurations = [];

    loadExecutions = function() {
        Algorithm_execution.getExecutions().success(function (data, status) {
            $scope.executions = data;
            console.log(data);
        });
    };
    loadProblem_configurations = function() {
        Problem_configuration.getProblem_configurations().success(function(data,status){
            console.log("aa");
            $scope.problem_configurations = data;
            console.log(data);
        })
    }
    loadMethod_configurations = function() {
        Method_configuration.getMethod_configurations().success(function(data,status){
            $scope.method_configurations = data;
            console.log(data);
        })
    }
    init = function() {
        console.log("bbssb");
        loadExecutions();
        loadMethod_configurations();
        loadProblem_configurations();
    }
    init();

    $scope.go = function ( path ) {
        $location.path( path );
    };
    $scope.compare = function () {
        console.log($scope.executions);
        var argument = "";
        for ( var i = 0; i<  $scope.executions.length; i++) {
            var execution = $scope.executions[i];

            if(execution.selected) {
                if(argument) {
                    argument = argument + "a" + execution.id;
                }
                else {
                    argument += execution.id;
                }
            }
        }

        $location.path("/comparestatistics/"+argument);
    }

});
