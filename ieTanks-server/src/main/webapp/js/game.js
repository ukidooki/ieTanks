var ieTanksVisualization = angular.module('ieTanksVisualization', []);

ieTanksVisualization.controller('GameCtrl', ['$scope', '$interval', 'REST',
    function ($scope, $interval, REST) {
        $scope.map = {border: 20, obstacles:[{type:'', x:5, y:10}]};

        var states = [{players:[{id:'blabla', action:'move', x:'10', y:'5'}, {id:'blabla2', action:'move', x:'3', y:'2'}], missiles:[]},
            {players:[{id:'blabla', action:'move', x:'6', y:'2'}, {id:'blabla2', action:'move', x:'11', y:'2'}], missiles:[]},
            {players:[{id:'blabla', action:'move', x:'15', y:'19'}, {id:'blabla2', action:'move', x:'5', y:'5'}], missiles:[]},
            {players:[{id:'blabla', action:'move', x:'0', y:'19'}, {id:'blabla2', action:'move', x:'7', y:'0'}], missiles:[]}];
        $interval(function () {
            var ind = Math.floor(Math.random()*states.length);
            $scope.state = states[ind];
        }, 3000);

    }
])
    .directive('tankGame', function () {
        return {
            scope: {
                map: '=',
                state: '='
            },
            link: function ($scope) {
                var gameBorder = 400;
                var tileSize = 32;
                var game, scale, scaledGrid, players, isInit;
                scale=(gameBorder/$scope.gridBorder)/tileSize;
                scaledGrid = tileSize * scale;
                isInit = true;

                var Player = function(id, tank, turret, direction) {
                    this.id = id;
                    this.tank = tank;
                    this.turret = turret;
                    this.direction = direction;
                };

                Player.prototype.moveTo = function (game, x, y, scale){
                    game.add.tween(this.tank).to({
                        x: Math.floor(tileSize*scale)*x,
                        y: Math.floor(tileSize*scale)*y
                    }, 250, Phaser.Easing.Quadratic.InOut, true);
                };


                game = new Phaser.Game(gameBorder, gameBorder, Phaser.CANVAS, 'game-window', {}, true);

                game.state.add('animation', {
                    preload: preload,
                    create: create });

                function preload() {
                    //load image which will be used as ground texture
                    game.load.image('ground', 'assets/scorched_earth.png');
                    game.load.image('tank', 'assets/tankBaseWhite.png');
                    game.load.image('turret', 'assets/tankTurretWhite.png');
                    game.load.image('wall', 'assets/tile_backwall.png');
                }

                function create() {
                    //Adds background to map (x_pos, y_pos, x_size, y_size) in pixels
                    game.add.tileSprite(0, 0, game.width, game.height, 'ground');
                }

                load_game();

                function load_game(map) {
                    if(map) {
                        isInit = true;
                        players = {};
                        scale = (gameBorder / map["border"]) / tileSize;
                        scaledGrid = tileSize * scale;
                        game.state.start('animation');
                    }
                }

                function sleep(milliseconds) {
                    var start = new Date().getTime();
                    for (var i = 0; i < 1e7; i++) {
                        if ((new Date().getTime() - start) > milliseconds){
                            break;
                        }
                    }
                }

                function load_state(state) {
                    if(state) {
                        var players_states = state["players"];
                        console.log(state);
                        console.log(players);
                        if(isInit) {
                            console.log("init");
                            players_states.forEach(function (state) {
                                var tank = game.add.sprite(state.x * scaledGrid, state.y * scaledGrid, 'tank');
                                var turret = game.add.sprite(0, 0, 'turret');
                                tank.addChild(turret);
                                tank.scale.x = scale;
                                tank.scale.y = scale;
                                var col = Phaser.Color.getRandomColor();
                                tank.tint = col;
                                turret.tint = col;
                                players[state.id] = new Player(state.id, tank, turret, 0);
                            });
                            isInit=false;
                        } else {
                            console.log("update");
                            players_states.forEach(function (state) {
                                players[state.id].moveTo(game, state.x, state.y, scale);
                            });
                        }

                    }
                }

                $scope.$watch('map', load_game, true);

                $scope.$watch('state', load_state);


                $scope.$on('$destroy', function() {
                    game.$destroy();
                });

            }
        }
    });


