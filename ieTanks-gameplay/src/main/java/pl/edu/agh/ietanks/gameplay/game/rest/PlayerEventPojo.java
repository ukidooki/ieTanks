package pl.edu.agh.ietanks.gameplay.game.rest;

import pl.edu.agh.ietanks.engine.api.events.AbstractTankEvent;

public class PlayerEventPojo {
    private final String playerId;
    private final String action;
    private final int x;
    private final int y;

    public PlayerEventPojo(AbstractTankEvent tankEvent) {
        this.playerId = tankEvent.tankId();
        this.action = tankEvent.getAction().getActionName();
        this.x = tankEvent.getDirection().getX();
        this.y = tankEvent.getDirection().getY();
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getAction() {
        return action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
