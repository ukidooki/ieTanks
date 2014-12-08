var ieTanksVisualization = angular.module('ieTanksVisualization', []);

ieTanksVisualization.controller('GameCtrl', ['$scope', '$interval', '$routeParams', 'REST',
    function ($scope, $interval, $routeParams, REST) {
        $scope.map = {
            border: 20,
            obstacles: [{type: '', x: 4, y: 9}, {type: '', x: 10, y: 7}, {type: '', x: 11, y: 7}, {
                type: '',
                x: 12,
                y: 7
            }]
        };

        //TODO uncomment when REST interface is ready
        //var gameInfo = REST.game.query({gameId: $routeParams.gameId}, function () {
        //    var step = 0,
        //        events = gameInfo['events'];
        //    $scope.map = gameInfo['map'];
        //    $interval(function () {
        //        if (step < events.length) {
        //            $scope.state = events[step];
        //            ++step;
        //        } else {
        //            console.log('No more events to display.');
        //        }
        //    }, 3000);
        //}, function () {
        //    console.log('Failed to load game events.');
        //});

        var states = [{
            players: [{id: 'blabla', action: 'move', x: '10', y: '5'}, {
                id: 'blabla2',
                action: 'move',
                x: '3',
                y: '2'
            }], missiles: []
        },
            {
                players: [{id: 'blabla', action: 'move', x: '6', y: '2'}, {
                    id: 'blabla2',
                    action: 'move',
                    x: '11',
                    y: '2'
                }], missiles: []
            },
            {
                players: [{id: 'blabla', action: 'move', x: '15', y: '19'}, {
                    id: 'blabla2',
                    action: 'move',
                    x: '5',
                    y: '5'
                }], missiles: []
            },
            {
                players: [{id: 'blabla', action: 'move', x: '0', y: '16'}, {
                    id: 'blabla2',
                    action: 'move',
                    x: '7',
                    y: '0'
                }], missiles: []
            },
            {
                players: [{id: 'blabla', action: 'move', x: '0', y: '14'}, {
                    id: 'blabla2',
                    action: 'shoot',
                    x: '7',
                    y: '0'
                }], missiles: [{player_id: 'blabla2', id: '1', x: '5', y: '4'}]
            },
            {
                players: [{id: 'blabla', action: 'move', x: '5', y: '12'}, {
                    id: 'blabla2',
                    action: 'move',
                    x: '0',
                    y: '10'
                }], missiles: [{player_id: 'blabla2', id: '1', x: '5', y: '12'}]
            },
            {players: [{id: 'blabla2', action: 'move', x: '7', y: '12'}], missiles: []},
            {players: [{id: 'blabla2', action: 'move', x: '0', y: '0'}], missiles: []}];

        var ind = 0;
        $interval(function () {
            //var ind = Math.floor(Math.random()*states.length);
            $scope.state = states[ind];
            ind = (1 + ind) % states.length;
        }, 3000);

    }
])
    .controller('GameHistory', ['$scope', '$interval', 'REST',
        function ($scope, $interval, REST) {
            var gameHistory = REST.games.query(function () {
                $scope.gameHistory = gameHistory;
            }, function () {
                console.log('Failed to retrieve finished games list.');
            });
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
                var mapTileSize = 128;
                var game, scale, scaledGrid, players, missiles, obstacles, isInit, mapScale, scaledMapGrid;
                scale = (gameBorder / $scope.gridBorder) / tileSize;
                mapScale = (gameBorder / $scope.gridBorder) / mapTileSize;
                scaledGrid = tileSize * scale;
                scaledMapGrid = mapTileSize * mapScale
                isInit = true;

                var Player = function (id, tank, turret, direction) {
                    this.id = id;
                    this.element = tank;
                    this.turret = turret;
                    this.direction = direction;
                };

                var Missile = function (id, bullet, direction) {
                    this.id = id;
                    this.element = bullet;
                    this.direction = direction;
                };

                var createPlayer = function (id, x, y) {
                    var tank = game.add.sprite(x * scaledGrid, y * scaledGrid, 'tank');
                    var turret = game.add.sprite(0, 0, 'turret');
                    tank.addChild(turret);
                    tank.scale.x = scale;
                    tank.scale.y = scale;
                    var color = Phaser.Color.getRandomColor();
                    tank.tint = color;
                    turret.tint = color;
                    return new Player(id, tank, turret, 0);
                };

                var createMissile = function (id, x, y) {
                    var bullet = game.add.sprite(x * scaledGrid, y * scaledGrid, 'bullet');
                    bullet.scale.x = scale;
                    bullet.scale.y = scale;
                    bullet.tint = Phaser.Color.getRandomColor();
                    return new Missile(id, bullet, 0);
                };

                Missile.prototype.moveTo = Player.prototype.moveTo = function (x, y) {
                    game.add.tween(this.element).to({
                        x: Math.floor(tileSize * scale) * x,
                        y: Math.floor(tileSize * scale) * y
                    }, 500, Phaser.Easing.Quadratic.InOut, true);
                };

                var removeOldItems = function (set, subset) {
                    var identifiers = subset.map(function (state) {
                        return state.id;
                    });
                    for (var item in set) {
                        if (set.hasOwnProperty(item) && identifiers.indexOf(item) === -1) {
                            set[item].element.destroy();
                            delete set[item];
                        }
                    }
                    return set;
                };

                game = new Phaser.Game(gameBorder, gameBorder, Phaser.CANVAS, 'game-window', {}, true);

                game.state.add('animation', {
                    preload: preload,
                    create: create
                });

                function preload() {
                    //load image which will be used as ground texture
                    game.load.image('ground', 'assets/Land_DirtGrass_main.png');
                    game.load.image('top_ground', 'assets/Land_DirtGrass_top.png');
                    game.load.image('left_ground', 'assets/Land_DirtGrass_left.png');
                    game.load.image('bottom_ground', 'assets/Land_DirtGrass_bottom.png');
                    game.load.image('right_ground', 'assets/Land_DirtGrass_right.png');
                    game.load.image('right_bot_ground', 'assets/Land_DirtGrass_right_bot.png');
                    game.load.image('right_top_ground', 'assets/Land_DirtGrass_right_top.png');
                    game.load.image('left_bot_ground', 'assets/Land_DirtGrass_left_bot.png');
                    game.load.image('left_top_ground', 'assets/Land_DirtGrass_left_top.png');
                    game.load.image('dirt_patch', 'assets/Land_DirtGrass_dirt_patch.png');
                    game.load.image('flower_1', 'assets/Flower (1).png');
                    game.load.image('flower_2', 'assets/Flower (2).png');
                    game.load.image('flower_3', 'assets/Flower (3).png');
                    game.load.image('flower_4', 'assets/Flower (4).png');
                    game.load.image('flower_5', 'assets/Flower (5).png');
                    game.load.image('flower_6', 'assets/Flower (6).png');
                    game.load.image('flower_7', 'assets/Flower (7).png');
                    game.load.image('flower_8', 'assets/Flower (8).png');
                    game.load.image('flower_9', 'assets/Flower (9).png');
                    game.load.image('flower_10', 'assets/Flower (10).png');
                    game.load.image('flower_11', 'assets/Flower (11).png');
                    game.load.image('flower_12', 'assets/Flower (12).png');
                    game.load.image('bullet', 'assets/bulletWhite.png');
                    game.load.image('tank', 'assets/tankBaseWhite.png');
                    game.load.image('turret', 'assets/tankTurretWhite.png');
                    game.load.image('wall', 'assets/Stone_wall.png');
                }

                function prepareStaticMap() {
                    game.add.tileSprite(0, 0, game.width, game.height, 'ground');
                    var s = game.add.tileSprite(scaledMapGrid, 0, game.width - scaledMapGrid * 2, scaledMapGrid, 'top_ground');
                    s.tileScale.y = mapScale;
                    s.tileScale.x = mapScale;
                    s = game.add.tileSprite(0, scaledMapGrid, scaledMapGrid, game.height - scaledMapGrid * 2, 'left_ground');
                    s.tileScale.y = mapScale;
                    s.tileScale.x = mapScale;
                    s = game.add.tileSprite(game.width - scaledMapGrid, scaledMapGrid, scaledMapGrid, game.height - scaledMapGrid * 2, 'right_ground');
                    s.tileScale.y = mapScale;
                    s.tileScale.x = mapScale;
                    s = game.add.tileSprite(scaledMapGrid, game.height - scaledMapGrid, game.width - scaledMapGrid * 2, scaledMapGrid, 'bottom_ground');
                    s.tileScale.y = mapScale;
                    s.tileScale.x = mapScale;
                    s = game.add.sprite(game.width - scaledMapGrid, game.height - scaledMapGrid, 'right_bot_ground');
                    s.scale.x = mapScale;
                    s.scale.y = mapScale;
                    s = game.add.sprite(game.width - scaledMapGrid, 0, 'right_top_ground');
                    s.scale.x = mapScale;
                    s.scale.y = mapScale;
                    s = game.add.sprite(0, game.height - scaledMapGrid, 'left_bot_ground');
                    s.scale.x = mapScale;
                    s.scale.y = mapScale;
                    s = game.add.sprite(0, 0, 'left_top_ground');
                    s.scale.x = mapScale;
                    s.scale.y = mapScale;

                    for (var i = 1; i < $scope.map["border"]; i++) {
                        var flower = Math.floor((Math.random() * 15) + 1);
                        var x = Math.floor((Math.random() * gameBorder));
                        var y = Math.floor((Math.random() * gameBorder));

                        if (flower >= 13) {
                            s = game.add.sprite(x, y, 'dirt_patch');
                            s.scale.x = mapScale;
                            s.scale.y = mapScale;
                        } else {
                            s = game.add.sprite(x, y, 'flower_' + flower.toString());
                            s.scale.x = mapScale * 2;
                            s.scale.y = mapScale * 2;
                        }

                    }

                }

                function create() {
                    prepareStaticMap();
                    //Adds background to map (x_pos, y_pos, x_size, y_size) in pixels
                    obstacles.forEach(function (obstacle) {
                        var obs = game.add.sprite(obstacle['x'] * scaledGrid, obstacle['y'] * scaledGrid, 'wall');
                        obs.scale.x = mapScale;
                        obs.scale.y = mapScale;
                    });

                }

                loadGame();

                function loadGame(map) {
                    if (!map) {
                        return;
                    }
                    isInit = true;
                    players = {};
                    missiles = {};
                    obstacles = $scope.map["obstacles"] || [];
                    scale = (gameBorder / $scope.map["border"]) / tileSize;
                    mapScale = (gameBorder / $scope.map["border"]) / mapTileSize;
                    scaledGrid = tileSize * scale;
                    scaledMapGrid = mapScale * mapTileSize;
                    game.state.start('animation');
                }

                function loadState(state) {
                    if (!state) {
                        return;
                    }

                    console.log(state); // FIXME debug info
                    if (isInit) {
                        console.log("init");
                        isInit = false;
                    } else {
                        console.log("update");
                    }

                    state['missiles'].forEach(function (state) {
                        if (!missiles.hasOwnProperty(state.id)) {
                            missiles[state.id] = createMissile(state.id, state.x, state.y);
                        }
                        missiles[state.id].moveTo(state.x, state.y);
                    });
                    state['players'].forEach(function (state) {
                        if (!players.hasOwnProperty(state.id)) {
                            players[state.id] = createPlayer(state.id, state.x, state.y);
                        }
                        players[state.id].moveTo(state.x, state.y);
                    });
                    players = removeOldItems(players, state['players']);
                    missiles = removeOldItems(missiles, state['missiles']);
                }

                $scope.$watch('map', loadGame, true);

                $scope.$watch('state', loadState);


                $scope.$on('$destroy', function () {
                    game.$destroy();
                });

            }
        }
    });


