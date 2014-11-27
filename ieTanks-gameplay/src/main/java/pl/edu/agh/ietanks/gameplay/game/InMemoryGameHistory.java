package pl.edu.agh.ietanks.gameplay.game;

import pl.edu.agh.ietanks.gameplay.game.api.Game;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;
import pl.edu.agh.ietanks.gameplay.game.innerapi.GameHistoryStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryGameHistory implements GameHistory, GameHistoryStorage {

    private Map<Integer, Game> historyGames = new HashMap<Integer, Game>();
    private ReentrantLock historyLock = new ReentrantLock();
    private int gameIdCounter = 0;

    @Override
    public List<Integer> getFinishedGamesIds() {
        try{
            historyLock.lock();

            List<Integer> finishedGameIds = new ArrayList<Integer>();
            finishedGameIds.addAll(historyGames.keySet());

            return finishedGameIds;

        }finally {
            historyLock.unlock();
        }
    }

    @Override
    public Game getGame(int gameId) {
        try{
            historyLock.lock();

            return historyGames.get(gameId);
        }finally {
            historyLock.unlock();
        }
    }

    @Override
    public int storeFinishedGame(Game game) {
        try{
            historyLock.lock();

            int gameId = gameIdCounter++;
            historyGames.put(gameId, game);

            return gameId;
        }finally {
            historyLock.unlock();
        }
    }
}
