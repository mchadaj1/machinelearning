/**
 * Created by mateusz on 04.03.16.
 */

    //var mlApp = angular.module('myApp');
angular.module('myApp').controller('configurationCtrl',function ($scope,$location,$routeParams, Problem, Problem_configuration,Problem_param) {
    $scope.configurations = [];
    $scope.problems = [];

    loadConfigs = function() {
        Problem_configuration.getProblem_configurations().success(function (data, status) {
            $scope.configurations = data;
        });
    };

    loadProblem_params = function(id){
        Problem_param.getProblem_params(id).success(function(data,status){
            $scope.problem_params = data;

            $scope.problem_params.forEach(function(problem_param){
//                console.log(problem_param);//
                console.log(problem_param.id);
                Problem_param.getValue($scope.configuration.id,problem_param.id).success(function(data,status){
                    console.log("value"+$scope.configuration.id+problem_param.id+data);
                    problem_param.value = data.value;

            })



            })
            console.log($scope.problem_params);
        })
    }

    init = function () {
        loadConfigs();
        //$scope.newproblemparam.primaryKey.problems_id="";
        //$scope.newproblemparam.primaryKey.name="";
        Problem.getProblems().success(function (data, status) {
            $scope.problems = data;
            console.log($scope.problems);

        });

        if ($routeParams.id) {
            Problem_configuration.getProblem_configuration($routeParams.id).success(function(data,status){
                $scope.configuration = data;
                loadProblem_params(data.problems_id);
            })

        }
        loadConfigs();
    };
    init();


    $scope.add = function () {
        Problem_configuration.createProblem_configuration($scope.configuration).success(function(data,success){

            $location.path("/configuration");
        })

    };
    $scope.delete = function(id){

        Problem_configuration.deleteConfiguration(id).success(function(data,status){
            Problem_configuration.getProblem_configurations().success(function (data, status) {
                $scope.configurations = data;
            });
        })
    };
    $scope.edit = function(id,configuration){

        var id = $routeParams.id;

        Problem_configuration.editProblemConfiguration(id,configuration).success(function() {

                $location.path("/configuration");
            }
        )
    };
    $scope.go = function ( path ) {
        $location.path( path );
    };



    $scope.addParam = function(){

        $scope.newproblemparam.primaryKey.problems_id=$scope.problem.id;
       
        Problem_param.createProblem_params($scope.newproblemparam).success(function(data,status){
            init();
            $scope.newproblemparam.primaryKey.name="";
            $scope.newproblemparam.type="";
        })
    };
    $scope.deleteParam = function(name){
        console.log(name);
        Problem_param.deleteParam($scope.problem.id,name).success(function(data,status){
            init();
        })

    };
    $scope.editValue = function(problem_param){
        //console.log(problem_param);
        Problem_param.setValue(problem_param,$scope.configuration.id).success(function (data,status) {
            console.log("Zaktualizowano value")
            console.log(data);

        })

    }
});
