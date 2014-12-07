package pl.edu.agh.ietanks.gameplay.game;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.engine.api.BoardDefinition;
import pl.edu.agh.ietanks.engine.api.Engine;
import pl.edu.agh.ietanks.engine.api.EngineFactory;
import pl.edu.agh.ietanks.engine.api.GameConfig;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.engine.api.events.Event;
import pl.edu.agh.ietanks.engine.api.events.RoundResults;
import pl.edu.agh.ietanks.gameplay.bot.BotExecutor;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.Game;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;
import pl.edu.agh.ietanks.gameplay.game.api.GameId;
import pl.edu.agh.ietanks.gameplay.game.innerapi.GameLogger;

import java.util.*;
import java.util.stream.Collectors;


class GameRunner implements Runnable, Game {

    private final GameId gameId;
    private final List<BotAlgorithm> bots;
    private final EngineFactory gameEngineFactory;
    private final GameHistory historyStorage;
    private final List<Event> gameEvents;
    private GameLogger LOGGER = new StandardOutputGameLogger();
    private Engine gameEngine;
    private final List<List<Event>> gameRoundsEvents;
    private final Board gameBoard;
    private final Map<String, Position> initialGamersPositions;

    public GameRunner(GameHistory historyStorage,
                      EngineFactory gameEngineFactory,
                      Board gameBoard,
                      List<BotAlgorithm> gameBots) {
        this.gameId = new GameId(UUID.randomUUID().toString());
        this.historyStorage = historyStorage;
        this.gameEngineFactory = gameEngineFactory;
        this.gameEvents = new ArrayList<>();
        this.gameBoard = gameBoard;
        this.bots = gameBots;
        this.gameRoundsEvents = new ArrayList<>();
        this.initialGamersPositions = new HashMap<>();
    }

    private BoardDefinition toBoardDefinition(Board board, List<BotAlgorithm> algorithms) {
        Preconditions.checkArgument(algorithms.size() <= board.getStartingPoints().size(), "More tanks than board supports!");

        int width = board.getWidth();
        int height = board.getHeight();

        final List<Position> goodPositions = board.getStartingPoints().stream().map(startingPoint ->
                        Position.topLeft().toDown(startingPoint.getY()).toRight(startingPoint.getX())
        ).collect(Collectors.toList());

        return new BoardDefinition(width, height, goodPositions);
    }
    
    private void setupEngineParams() {
        BoardDefinition gameBoardDefinition = toBoardDefinition(gameBoard, bots);

        gameEngine = gameEngineFactory.createEngineInstance(gameBoardDefinition,
                Lists.transform(bots, botAlgorithm -> new BotExecutor(botAlgorithm.id(), botAlgorithm.pythonCode())),
                GameConfig.newBuilder().withTurnsLimit(10).createGameConfig());
        gameEngine.currentBoard().tankIds().forEach(tankId -> initialGamersPositions.put(tankId, gameEngine.currentBoard().findTank(tankId).get()));

        LOGGER.startGame();
    }

    @Override
    public void run() {
        setupEngineParams();

        RoundResults rResults;
        while ((rResults = gameEngine.nextMove()) != null && !rResults.isGameFinished()) {
            List<Event> roundEvents = rResults.getRoundEvents();
            gameEvents.addAll(roundEvents);
            gameRoundsEvents.add(Collections.unmodifiableList(roundEvents));

            LOGGER.nextRoundResults(rResults, gameEngine.currentBoard());
        }

        historyStorage.storeFinishedGame(this);
    }
        
    @Override
    public GameId getId() {
        return gameId;
    }

    @Override
    public List<Event> getGameEvents() {
        return Collections.unmodifiableList(gameEvents);
    }

    @Override
    public List<BotAlgorithm> getGameParticipants() {
        return Collections.unmodifiableList(bots);
    }

    @Override
    public Board getGameBoard() {
        return gameBoard;
    }

    @Override
    public List<List<Event>> getGameEventsByRound() {
        return Collections.unmodifiableList(gameRoundsEvents);
    }

    @Override
    public Map<String, Position> getInitialParticipantsPositions() {
        return Collections.unmodifiableMap(initialGamersPositions);
    }
}
