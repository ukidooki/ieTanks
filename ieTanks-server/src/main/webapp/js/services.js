var ieTanksServices = angular.module('ieTanksServices', []);

ieTanksServices.factory('REST', ['$resource',
    function ($resource) {
        return {
            listEvents: $resource('/api/events/:gameId.:format', { format: 'json' }),
            listGames: $resource('/api/games.:format', { format: 'json' }),
            // add more calls to external components
        };
    }
]);