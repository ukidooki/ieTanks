package pl.edu.agh.ietanks.engine.testutils;

import com.google.common.collect.Lists;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Bot;

import java.util.ArrayDeque;
import java.util.Queue;

public class BotBuilder {
    public static Bot fromSequence(final Action... actions) {
        return new Bot() {
            final Queue<Action> actionQueue = new ArrayDeque<>(Lists.newArrayList(actions));

            @Override
            public Action performAction(Board currentBoard) {
                if(actionQueue.isEmpty()) {
                    return Action.NoOperation;
                }
                else {
                    return actionQueue.poll();
                }
            }
        };
    }
}
