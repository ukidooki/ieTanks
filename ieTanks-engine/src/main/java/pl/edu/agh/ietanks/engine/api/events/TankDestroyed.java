package pl.edu.agh.ietanks.engine.api.events;

import pl.edu.agh.ietanks.engine.api.Direction;

/**
 * Indicates that a tank has been destroyed.
 */
public class TankDestroyed extends AbstractTankEvent {

    public TankDestroyed(String tankId) {
        super(TankAction.DESTROYED, tankId, Direction.None);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TankDestroyed that = (TankDestroyed) o;

        if (tankId != null ? !tankId.equals(that.tankId) : that.tankId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tankId != null ? tankId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TankDestroyed {tankId=" + tankId + "}";
    }

}
