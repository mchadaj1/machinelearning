/**
 * Kontroler pozwalający na zarządzanie konfiguracjami metod.
 * Created by mateusz on 04.03.16.
 */

    //var mlApp = angular.module('myApp');
angular.module('myApp').controller('methodconfigurationCtrl',function ($scope,$location,$routeParams, Method, Method_configuration,Method_param) {
    $scope.configurations = [];
    $scope.configuration = {};
    $scope.methods = [];
    //$scope.configuration = {};
    loadConfigs = function() {
        Method_configuration.getMethod_configurations().success(function (data, status) {
            $scope.configurations = data;
        });
    };

    loadMethodParams = function(id){
        Method_param.getMethod_params(id).success(function(data,status){
            $scope.method_params = data;

            $scope.method_params.forEach(function(method_param){
//                console.log(problem_param);//
                console.log(method_param.id);
                Method_param.getValue($scope.configuration.id,method_param.id).success(function(data,status){
                    console.log("value"+$scope.configuration.id+method_param.id+data);
                    method_param.value = data.value;

            })

            })
            console.log($scope.method_params);
        })
    }

    init = function () {
        loadConfigs();
        //$scope.newproblemparam.primaryKey.problems_id="";
        //$scope.newproblemparam.primaryKey.name="";
        Method.getMethods().success(function (data, status) {
            $scope.methods = data;
            console.log($scope.methods);

        });

        if ($routeParams.id) {
            Method_configuration.getMethod_configuration($routeParams.id).success(function(data,status){
                $scope.configuration = data;
                loadMethodParams(data.method_id);
            })

        }
        else {
            $scope.configuration.code="Position myPosition = (Position)mapInfo.get(\"position\");\n" +
                "Position newPosition = new Position(myPosition);\n\nList<Position> preys = (List<Position>)mapInfo.get(\"preys\");" +
                "\nList<Position> predators = (List<Position>)mapInfo.get(\"predators\");\nInteger lastMovePoints = (Integer) mapInfo.get(\"lastMovePoints\");   \n\nnewPosition.setX(myPosition.getX()+x); \nnewPosition.setY(myPosition.getY()+y); \n return newPosition;";
        }
        loadConfigs();
    };
    init();


    $scope.add = function () {

        Method_configuration.createMethod_configuration($scope.configuration).success(function(data,success){

            $location.path("/methodconfiguration");
        })

    };
    $scope.delete = function(id){

        Method_configuration.deleteConfiguration(id).success(function(data,status){
            Method_configuration.getMethod_configurations().success(function (data, status) {
                $scope.configurations = data;
            });
        })
    };
    $scope.edit = function(id,configuration){

        var id = $routeParams.id;

        Method_configuration.editMethodConfiguration(id,configuration).success(function() {

                $location.path("/methodconfiguration");
            }
        )
    };
    $scope.go = function ( path ) {
        $location.path( path );
    };




    $scope.editValue = function(method_param){
        //console.log(problem_param);
        Method_param.setValue(method_param,$scope.configuration.id).success(function (data,status) {
            console.log("Zaktualizowano value")
            console.log(data);

        })

    }
});
