package pl.edu.agh.ietanks.gameplay.game;

import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.GamePlay;
import pl.edu.agh.ietanks.gameplay.game.innerapi.GameHistoryStorage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SimpleGamePlay implements GamePlay {
    private static final int THREADS_IN_POOL = 5;

    private final GameHistoryStorage historyStorage = new InMemoryGameHistory();

    private final ExecutorService executionService;

    public SimpleGamePlay(){
        executionService = Executors.newFixedThreadPool(THREADS_IN_POOL);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                executionService.shutdown();
            }
        });
    }

    @Override
    public void startGame(Board gameBoard, List<BotAlgorithm> bots) {
        executionService.execute(new GameRunner(historyStorage, gameBoard, bots));
    }
}
