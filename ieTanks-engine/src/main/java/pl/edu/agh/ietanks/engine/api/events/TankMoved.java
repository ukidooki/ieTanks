package pl.edu.agh.ietanks.engine.api.events;


import pl.edu.agh.ietanks.engine.api.Direction;

/**
 * Indicates that a tank has moved.
 */
public class TankMoved extends AbstractTankEvent {
    private final int step;

    public TankMoved(String tankId, Direction direction, int step) {
        super(TankAction.MOVED, tankId, direction);
        this.step = step;
    }

    public int step() {
        return step;
    }

    @Override
    public String toString() {
        return "TankMoved{" +
                "tankId=" + tankId +
                ", direction=" + direction +
                ", step=" + step +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TankMoved tankMoved = (TankMoved) o;

        if (step != tankMoved.step) return false;
        if (direction != tankMoved.direction) return false;
        if (tankId != null ? !tankId.equals(tankMoved.tankId) : tankMoved.tankId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tankId != null ? tankId.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + step;
        return result;
    }
}
