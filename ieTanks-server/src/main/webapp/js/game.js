var ieTanksVisualization = angular.module('ieTanksVisualization', []);

ieTanksVisualization.controller('GameCtrl', ['$scope',
    function($scope) {
        $scope.placeholderText = game
    }
]);


var game = new Phaser.Game(320, 320, Phaser.CANVAS, 'tanks-map', { preload: preload, create: create, update: update, render: render });



function preload() {

    //load image which will be used as ground texture
    game.load.image('ground', 'http://examples.phaser.io/assets/games/tanks/scorched_earth.png');

}



var map;
var layer;



function create() {

    //Creates a blank tilemap
    map = game.add.tilemap();

    //Adds background to map (x_pos, y_pos, x_size, y_size) in pixels
    game.add.tileSprite(0, 0, 320, 320, 'ground');

    //  Creates a new blank layer and sets the map dimensions.
    //  In this case the map is 10x10 tiles in size and the tiles are 32x32 pixels in size each.
    layer = map.create('level-ground', 10, 10, 32, 32);
    //  Resize the world
    layer.resizeWorld();

}

function update() {

    //Add logic on update here

}

function render() {


}
