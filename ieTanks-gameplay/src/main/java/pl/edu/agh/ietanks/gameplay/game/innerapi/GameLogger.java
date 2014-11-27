package pl.edu.agh.ietanks.gameplay.game.innerapi;

import pl.edu.agh.ietanks.engine.api.events.RoundResults;

public interface GameLogger {
    public void startGame();
    public void nextRoundResults(RoundResults results);
}
