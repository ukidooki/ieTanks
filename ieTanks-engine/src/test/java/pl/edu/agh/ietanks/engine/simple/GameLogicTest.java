package pl.edu.agh.ietanks.engine.simple;

import org.junit.Test;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.events.Event;
import pl.edu.agh.ietanks.engine.api.events.TankMoved;
import pl.edu.agh.ietanks.engine.testutils.BoardBuilder;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class GameLogicTest {
    @Test
    public void shouldMoveTankRightToAFreePlace() throws Exception {
        //given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                "....");

        GameLogic logic = new GameLogic(board);

        // when
        final List<Event> events = logic.tryApplyAction(Action.MoveRight, 0);

        // then
        assertThat(logic.board()).isEqualTo(BoardBuilder.fromASCII(
                "....",
                "..0.",
                "....",
                "...."));

        assertThat(events).containsExactly(new TankMoved(0, Board.Direction.Right));
    }
}