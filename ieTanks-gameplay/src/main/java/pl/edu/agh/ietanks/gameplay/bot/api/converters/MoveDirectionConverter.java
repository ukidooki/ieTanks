package pl.edu.agh.ietanks.gameplay.bot.api.converters;

import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.gameplay.bot.api.MoveDirection;

public class MoveDirectionConverter {

    public Board.Direction convertToEngineDirection(MoveDirection moveDirection) {
        switch (moveDirection) {
            case North:
                return Board.Direction.Up;
            case East:
                return Board.Direction.Right;
            case South:
                return Board.Direction.Down;
            case West:
                return Board.Direction.Left;
        }
        return null;
    }
}
