package pl.edu.agh.ietanks.engine.api.events;

import pl.edu.agh.ietanks.engine.api.Board;

/**
 * Indicates that a tank has moved.
 */
public class TankMoved implements Event {
    private final int tankId;
    private final Board.Direction direction;

    public TankMoved(int tankId, Board.Direction direction) {
        this.tankId = tankId;
        this.direction = direction;
    }

    public int tankId() {
        return tankId;
    }

    public Board.Direction direction() {
        return direction;
    }

    @Override
    public String toString() {
        return "TankMoved{" +
                "tankId=" + tankId +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TankMoved)) return false;

        TankMoved tankMoved = (TankMoved) o;

        if (tankId != tankMoved.tankId) return false;
        if (direction != tankMoved.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tankId;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
