package pl.edu.agh.ietanks.gameplay.game.rest;


import pl.edu.agh.ietanks.engine.api.events.AbstractMissileEvent;

public class MissileEventPojo {
    private final String action;
    private final int missileId;
    private final int x;
    private final int y;

    public MissileEventPojo(AbstractMissileEvent missileEvent) {
        this.action = missileEvent.getAction().getActionName();
        this.missileId = missileEvent.id();
        this.x = missileEvent.position().fromLeft();
        this.y = missileEvent.position().fromTop();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getMissileId() {
        return missileId;
    }

    public String getAction() {
        return action;
    }
}
