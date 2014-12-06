package pl.edu.agh.ietanks.engine.api.events;

import pl.edu.agh.ietanks.engine.api.Direction;
import pl.edu.agh.ietanks.engine.api.Position;

public abstract class AbstractMissileEvent implements Event {

    protected final int id;
    protected final Direction direction;
    protected final Position position;
    protected final int speed;

    public AbstractMissileEvent(int id, Position position, Direction direction, int speed) {
        this.id = id;
        this.direction = direction;
        this.speed = speed;
        this.position = position;
    }

    @Override
    public EventGroup getEventGroup() {
        return EventGroup.MISSILES;
    }


    public int id() {
        return id;
    }

    public Position position() {
        return position;
    }

    public Direction direction() {
        return direction;
    }

    public int speed() {
        return speed;
    }
}
