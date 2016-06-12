/**
 * Created by mateusz on 22.03.16.
 */

angular.module('myApp').controller('mainCtrl', function ($scope, $http,Menu){

    $scope.mainMenu = [
        {
            'link':'/problem',
            'ico':'/ fa-hand-o-right',
            'name':'Zadania',


        },
        {
            'link':'/method',
            'ico':'/ fa-hand-o-right',
            'name':'Metody',

        },
        {
            'link':'/methodconfiguration',
            'ico':'/ fa-hand-o-right',
            'name':'Warianty metod',

        },
        {
            'link':'/configuration',
            'ico':'/ fa-hand-o-right',
            'name':'Warianty zadań',

        },
        {
            'link':'/executions',
            'ico':'/ fa-hand-o-right',
            'name':'Przeprowadzone symulacje',

        }];

});