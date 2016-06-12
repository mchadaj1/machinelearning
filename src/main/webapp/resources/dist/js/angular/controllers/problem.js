/**
 * Created by mateusz on 04.03.16.
 */

    //var mlApp = angular.module('myApp');
angular.module('myApp').controller('problemCtrl',function ($scope,$location,$routeParams, Problem, Problem_param) {
    $scope.problems = [];
    $scope.problem = {
        params :[]
    }
    $scope.problem_params = [];
    //$scope.newproblemparam;


    problemSelected = function(id){
        Problem.getProblem(id).success(function(data,status){
            $scope.problem=data;
        });

        Problem_param.getProblem_params(id).success(function(data,status){

            $scope.problem_params = data;
            console.log(data);
        })

    };
    var updateProblems = function() {
        Problem.getProblems().success(function (data, status) {
            $scope.problems = data;
        });
    }
    var init = function () {
        //$scope.newproblemparam.primaryKey.problems_id="";
        //$scope.newproblemparam.primaryKey.name="";
        if ($routeParams.id) {
            problemSelected($routeParams.id);
        }else
        updateProblems();
    };
    init();


    $scope.add = function () {
        Problem.createProblem($scope.problem).success(function (data, status) {
          $scope.problem=null;


            $location.path("/problem");

        })
    };
    $scope.delete = function(id){

        Problem.deleteProblem(id).success(function(data,status){
            Problem.getProblems().success(function (data, status) {
                $scope.problems = data;
            });
        })
    };
    $scope.edit = function(id,problem){

        var id = $routeParams.id;

        Problem.editProblem(id,problem).success(function() {
            Problem.getProblems().success(function (data, status) {
                $scope.problems = data;
            });
                $location.path("/problem");
            }
        )
    };
    $scope.go = function ( path ) {
        $location.path( path );
    };



    $scope.addParam = function(){

        $scope.newproblemparam.problemsid=$scope.problem.id;

        Problem_param.createProblem_params($scope.newproblemparam).success(function(data,status){
            if($scope.problem_params.length===0)
                $scope.problem_params = [data];
            else
                $scope.problem_params.push(data);
            $scope.newproblemparam.name="";
            $scope.newproblemparam.type="";
        })
    };
    $scope.deleteParam = function(id,param){
        console.log(id);
        Problem_param.deleteParam(id).success(function(data,status){

            var index = $scope.problem_params.indexOf(param);
            console.log(param);
            $scope.problem_params.splice(index, 1);

        })

    };
});
