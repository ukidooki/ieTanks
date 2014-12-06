package pl.edu.agh.ietanks.gameplay.game;

import com.google.common.base.Preconditions;
import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.engine.api.BoardDefinition;
import pl.edu.agh.ietanks.engine.api.Engine;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameRunnerFactory {

    private final GameHistory storage;

    public GameRunnerFactory(GameHistory storage) {
        this.storage = storage;
    }

    public GameRunner create(Board board, List<BotAlgorithm> algorithms, Engine gameEngine) {
        BoardDefinition boardDefinition = toBoardDefinition(board, algorithms);
        GameRunner gameRunner = new GameRunner(storage, gameEngine, boardDefinition, algorithms);

        return gameRunner;
    }

    private BoardDefinition toBoardDefinition(Board board, List<BotAlgorithm> algorithms) {
        Preconditions.checkArgument(algorithms.size() <= board.getStartingPoints().size(), "More tanks than board supports!");
        Map<String, Position> tanks = new HashMap<>();
        int width = board.getWidth();
        int height = board.getHeight();

        final List<Position> goodPositions = board.getStartingPoints().stream().map(startingPoint ->
                        Position.topLeft().toDown(startingPoint.getY()).toRight(startingPoint.getX())
        ).collect(Collectors.toList());

        int i = 0;
        for (BotAlgorithm algorithm : algorithms) {
            tanks.put(algorithm.id().id(), goodPositions.get(i));
            i++;
        }

        return new BoardDefinition(width, height, tanks);
    }
}
