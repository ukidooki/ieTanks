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
    function ($routeProvider) {
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
    function ($authProvider) {
        $authProvider.google({
            url: 'api/auth/google',
            clientId: '989200984574-h79sl47vm75o4ffo1ccj4timmmtfn4oc.apps.googleusercontent.com'
        });
    }
);

ieTanksApp.controller('NavbarCtrl', function ($scope, $auth) {
    $scope.isAuthenticated = function () {
        return $auth.isAuthenticated();
    };
});

ieTanksApp.controller('LoginCtrl', function ($scope, $auth) {
    $scope.authenticate = function (provider) {
        $auth.authenticate(provider)
            .then(function () {
                alert('You have successfully logged in');
            })
            .catch(function (response) {
                alert('Login failure');
            });
    };
});

ieTanksApp.controller('LogoutCtrl', function ($auth) {
    if (!$auth.isAuthenticated()) {
        return;
    }
    $auth.logout()
        .then(function () {
            alert('You have been logged out');
        });
});

ieTanksApp.run(function ($rootScope, $location) {
    $rootScope.newGameId = Math.floor(Math.random() * 1000); // FIXME get ID from server instead?
    $rootScope.isActive = function (viewLocation) {
        return $location.path().indexOf(viewLocation) === 0;
    };
});