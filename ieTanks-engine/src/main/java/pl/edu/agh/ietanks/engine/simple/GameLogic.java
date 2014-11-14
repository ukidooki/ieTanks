package pl.edu.agh.ietanks.engine.simple;

import com.google.common.base.Optional;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.events.Event;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.engine.api.events.TankMoved;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private MutableBoard board;

    public GameLogic(MutableBoard board) {
        this.board = board;
    }

    public List<Event> moveMissiles() {
        return new ArrayList<>();
    }

    public Board board() {
        return board;
    }

    public List<Event> tryApplyAction(Action proposedAction, int botId) {
        List<Event> events = new ArrayList<>();

        Optional<Position> botPosition = board.findTank(botId);

        if(!botPosition.isPresent()) {
            return events;
        }

        if(proposedAction == Action.MoveRight) {
            Position destination = botPosition.get().oneRight();

            if(board.isWithin(destination) && board.isAccessibleForTank(destination)) {
                board.replaceTank(botId, destination);
                events.add(new TankMoved(botId, Board.Direction.Right));
            }
        }

        return events;
    }
}
