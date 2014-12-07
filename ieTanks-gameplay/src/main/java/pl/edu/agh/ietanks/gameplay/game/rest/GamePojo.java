package pl.edu.agh.ietanks.gameplay.game.rest;

import pl.edu.agh.ietanks.gameplay.game.api.Game;

import java.util.ArrayList;
import java.util.List;

public class GamePojo {

    private MapPojo map;
    private List<GameRoundPojo> events = new ArrayList<GameRoundPojo>();

    public GamePojo(Game game) {
        map = new MapPojo(game.getGameBoard());
        game.getGameEventsByRound().forEach((roundEvents) -> events.add(new GameRoundPojo(roundEvents)));
    }


    public List<GameRoundPojo> getEvents() {
        return events;
    }

    public MapPojo getMap() {
        return map;
    }
}
