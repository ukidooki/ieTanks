package pl.edu.agh.ietanks.gameplay.bot.api.converters;

import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.gameplay.bot.api.Shot;
import pl.edu.agh.ietanks.gameplay.bot.api.ShotDirection;

public class ShotDirectionConverter {

    public Board.Direction convertToEngineDirection(ShotDirection shotDirection) {
        switch (shotDirection) {
            case North:
                return Board.Direction.Up;
            case NorthEast:
                return Board.Direction.Up_Right;
            case East:
                return Board.Direction.Right;
            case SouthEast:
                return Board.Direction.Down_Right;
            case South:
                return Board.Direction.Down;
            case SouthWest:
                return Board.Direction.Down_Left;
            case West:
                return Board.Direction.Left;
            case NorthWest:
                return Board.Direction.Up_Left;
        }
        return null;
    }

    public ShotDirection convertToGameplayDirection(Board.Direction direction) {
        switch (direction) {
            case Right:
                return ShotDirection.East;
            case Left:
                return ShotDirection.West;
            case Up:
                return ShotDirection.North;
            case Down:
                return ShotDirection.South;
            case Up_Right:
                return ShotDirection.NorthEast;
            case Up_Left:
                return ShotDirection.NorthWest;
            case Down_Right:
                return ShotDirection.SouthEast;
            case Down_Left:
                return ShotDirection.SouthWest;
        }
        return null;
    }
}
