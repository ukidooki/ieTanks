'use strict';

/* App Module */

var ieTanksApp = angular.module('ieTanksApp', [
    'ieTanksVisualization',
    'ieTanksServices',
    'ngRoute',
    'ngResource',
    'satellizer'
]); // add other modules if dependant here

ieTanksApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/game/:gameId', {
                templateUrl: 'html/game.html',
                controller: 'GameCtrl'
            }).
            when('/history', {
                templateUrl: 'html/history.html',
                controller: 'GameHistory'
            }).
            when('/login', {
                url: '/login',
                templateUrl: 'html/login.html',
                controller: 'LoginCtrl'
            }).
            when('/logout', {
                url: '/logout',
                template: null,
                controller: 'LogoutCtrl'
            }).
            otherwise({
                redirectTo: '/'
            });
    }
]);

ieTanksApp.config(
    function($authProvider) {
        $authProvider.github({
            clientId: 'a8bdaeaa63c864ee885f'
        });
    }
);

ieTanksApp.controller('NavbarCtrl', function($scope, $auth) {
        $scope.isAuthenticated = function() {
            return $auth.isAuthenticated();
        };
    });

ieTanksApp.controller('LoginCtrl', function($scope, $auth) {
        $scope.authenticate = function(provider) {
            $auth.authenticate(provider)
                .then(function() {
                    alert({
                        content: 'You have successfully logged in',
                        animation: 'fadeZoomFadeDown',
                        type: 'material',
                        duration: 3
                    });
                })
                .catch(function(response) {
                    alert({
                        content: response.data.message,
                        animation: 'fadeZoomFadeDown',
                        type: 'material',
                        duration: 3
                    });
                });
        };
    });

ieTanksApp.controller('LogoutCtrl', function($auth, $alert) {
        if (!$auth.isAuthenticated()) {
            return;
        }
        $auth.logout()
            .then(function() {
                $alert({
                    content: 'You have been logged out',
                    animation: 'fadeZoomFadeDown',
                    type: 'material',
                    duration: 3
                });
            });
    });

ieTanksApp.run(function($rootScope, $location) {
    $rootScope.newGameId = Math.floor(Math.random()*1000); // FIXME get ID from server instead?
    $rootScope.isActive = function (viewLocation) {
        return $location.path().indexOf(viewLocation) === 0;
    };
});