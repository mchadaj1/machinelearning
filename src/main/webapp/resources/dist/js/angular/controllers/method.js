/**
 * Created by mateusz on 04.03.16.
 */

    //var mlApp = angular.module('myApp');
angular.module('myApp').controller('methodCtrl',function ($scope,$location,$routeParams, Method,Method_param, Problem) {
    $scope.methods = [];
    $scope.problems = [];
    $scope.method_params = [];
    $scope.method = {
        problem_id: null,
        name: "",
        problem: null
    }
    $scope.methodSelected = function(id){
        Method.getMethod(id).success(function(data,status){
            $scope.method=data;
        });

        Method_param.getMethod_params(id).success(function(data,status){

            $scope.method_params = data;
            console.log(data);
        })

    };
    problemSelected2 = function(problem_id){
        Problem.getProblem(problem_id).success(function(data,status){
            $scope.method.problem = data;
        })
    }

    updateMethods = function() {
        Method.getMethods().success(function (data, status) {
            $scope.methods = data;

        });
    };
    init = function () {
        Problem.getProblems().success(function (data, status) {
            $scope.problems = data;
            console.log($scope.problems);

        });
        $scope.method_params = [];

        //$scope.newproblemparam.primaryKey.problems_id="";
        //$scope.newproblemparam.primaryKey.name="";
        if ($routeParams.id) {
            $scope.methodSelected($routeParams.id);
        } else {
            $scope.method.code="Position myPosition = (Position)mapInfo.get(\"position\");\n" +
                "Position newPosition = new Position(myPosition);\n\nList<Position> preys = (List<Position>)mapInfo.get(\"preys\");" +
                "\nList<Position> predators = (List<Position>)mapInfo.get(\"predators\");\nInteger lastMovePoints = (Integer) mapInfo.get(\"lastMovePoints\");   \n\nnewPosition.setX(myPosition.getX()+x); \nnewPosition.setY(myPosition.getY()+y); \n return newPosition;";
            $scope.method.imports="import com.example.piqle.src.algorithms.AbstractMemorySelector;";
        }
        if($routeParams.problem_id){
            $scope.method.problem_id = $routeParams.problem_id;
            problemSelected2($routeParams.problem_id);
        }

        updateMethods();


    };
    init();



    $scope.add = function () {
        //$scope.method.problem_id=1;
        console.log($scope.method);
        $scope.method.user_id=1;
        Method.createMethod($scope.method).success(function (data, status) {
            $scope.method=null;

            updateMethods();
            $location.path("/method");

        })
    };
    $scope.delete = function(id){

        Method.deleteMethod(id).success(function(data,status){
            Method.getMethods().success(function (data, status) {
                $scope.methods = data;
            });
        })
    };
    $scope.edit = function(id,method){

        var id = $routeParams.id;

        Method.editMethod(id,method).success(function() {
                Method.getMethods().success(function (data, status) {
                    $scope.methods = data;
                });
                $location.path("/method");
            }
        )
    };
    $scope.go = function ( path ) {
        $location.path( path );
    };



    $scope.addParam = function(){

        $scope.newmethodparam.methodsid=$scope.method.id;
    console.log($scope.newmethodparam);
        Method_param.createMethod_params($scope.newmethodparam).success(function(data,status){
        //    init();
            //if($scope.method_params==[])
            if($scope.method_params.length===0)
                $scope.method_params = [data];
            else
                $scope.method_params.push(data);
            $scope.newmethodparam.name="";
            $scope.newmethodparam.type="";
        })
    };
    $scope.deleteParam = function(id,param){
        console.log(name);
        Method_param.deleteParam(id).success(function(data,status){
            var index = $scope.method_params.indexOf(param);
            $scope.method_params.splice(index, 1);
        })

    };
});
/**
 * Created by mateusz on 05.03.16.
 */
