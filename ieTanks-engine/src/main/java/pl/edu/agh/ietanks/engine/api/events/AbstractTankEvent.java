package pl.edu.agh.ietanks.engine.api.events;


public abstract class AbstractTankEvent implements Event {

    protected final String tankId;

    public AbstractTankEvent(String tankId) {
        this.tankId = tankId;
    }

    @Override
    public EventGroup getEventGroup() {
        return EventGroup.TANKS;
    }

    public String tankId() {
        return tankId;
    }
}
