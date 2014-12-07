package pl.edu.agh.ietanks.gameplay.game;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.engine.api.BoardDefinition;
import pl.edu.agh.ietanks.engine.api.EngineFactory;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameRunnerFactory {
    private final GameHistory storage;
    private final EngineFactory engineFactory;

    @Autowired
    public GameRunnerFactory(GameHistory storage, EngineFactory engineFactory) {
        this.storage = storage;
        this.engineFactory = engineFactory;
    }

    public GameRunner create(Board board, List<BotAlgorithm> algorithms) {
        BoardDefinition boardDefinition = toBoardDefinition(board, algorithms);
        GameRunner gameRunner = new GameRunner(storage, engineFactory, boardDefinition, algorithms);

        return gameRunner;
    }

    private BoardDefinition toBoardDefinition(Board board, List<BotAlgorithm> algorithms) {
        Preconditions.checkArgument(algorithms.size() <= board.getStartingPoints().size(), "More tanks than board supports!");

        int width = board.getWidth();
        int height = board.getHeight();

        final List<Position> goodPositions = board.getStartingPoints().stream().map(startingPoint ->
                        Position.topLeft().toDown(startingPoint.getY()).toRight(startingPoint.getX())
        ).collect(Collectors.toList());

        final List<Position> obstaclesPositions = board.getObstacles().stream().map(obstaclePoint ->
                        Position.topLeft().toDown(obstaclePoint.getY()).toRight(obstaclePoint.getX())
        ).collect(Collectors.toList());

        return new BoardDefinition(width, height, goodPositions, obstaclesPositions);
    }
}
