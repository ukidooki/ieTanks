'use strict';

/* App Module */

var ieTanksApp = angular.module('ieTanksApp', [
    'ieTanksVisualization',
    'ieTanksServices',
    'ngRoute',
    'ngResource'
]); // add other modules if dependant here

ieTanksApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/game', {
                templateUrl: 'html/game.html',
                controller: 'GameCtrl'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);