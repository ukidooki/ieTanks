package pl.edu.agh.ietanks.gameplay.board;

import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.engine.api.BoardDefinition;
import pl.edu.agh.ietanks.engine.api.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDefinitionAdapter implements BoardDefinition {
    private final Board board;

    public BoardDefinitionAdapter(Board board) {
        this.board = board;
    }

    @Override
    public int width() {
        return board.getWidth();
    }

    @Override
    public int height() {
        return board.getWidth();
    }

    @Override
    public Map<Integer, Position> initialTankPositions() {
        //TODO(mequrel): add tanks positions
        return new HashMap<>();
    }
}
