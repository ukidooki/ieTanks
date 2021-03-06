package pl.edu.agh.ietanks.engine.simple;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Bot;
import pl.edu.agh.ietanks.engine.api.Engine;
import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.events.Event;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SimpleEngine implements Engine {
    private GameLogic gameLogic;
    private Map<Bot, Integer> botIds = new HashMap<>();
    private Queue<Bot> turns = new ArrayDeque<>();

    @Override
    public void setup(MutableBoard initialBoard, List<Bot> bots) {
        this.gameLogic = new GameLogic(initialBoard);

        int id = 0;
        for(Bot bot : bots) {
            turns.add(bot);
            botIds.put(bot, id++);
        }
    }

    @Override
    public List<Event> nextMove() {
        Bot currentBot = turns.poll();
        int botId = botIds.get(currentBot);

        List<Event> missileEvents = gameLogic.moveMissiles();

        Action proposedAction = currentBot.performAction(gameLogic.board());
        List<Event> tankEvents = gameLogic.tryApplyAction(proposedAction, botId);

        turns.add(currentBot);

        return Lists.newArrayList(Iterables.concat(missileEvents, tankEvents));
    }

    @Override
    public Board currentBoard() {
        return gameLogic.board();
    }
}
