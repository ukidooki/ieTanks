package pl.edu.agh.ietanks.engine.simple;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Bot;
import pl.edu.agh.ietanks.engine.api.Engine;
import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.events.Event;
import pl.edu.agh.ietanks.engine.api.events.TankMoved;
import pl.edu.agh.ietanks.engine.simple.actions.Move;
import pl.edu.agh.ietanks.engine.testutils.BoardBuilder;
import pl.edu.agh.ietanks.engine.testutils.BotBuilder;

import com.google.common.collect.Lists;

public class SimpleEngineTest {
    @Test
    public void shouldMoveTanksInTurns() throws Exception {
        // given
        MutableBoard startingBoard = BoardBuilder.fromASCII(
                "....",
                "01..",
                "....",
                "....");

        Bot bot1 = BotBuilder.fromSequence(new Move(Board.Direction.Right, 1));
        Bot bot2 = BotBuilder.fromSequence(new Move(Board.Direction.Right, 1));

        Engine engine = new SimpleEngine();
        engine.setup(startingBoard, Lists.newArrayList(bot1, bot2));

        // when
        final List<Event> events1 = engine.nextMove();

        // then
        assertThat(engine.currentBoard()).isEqualTo(startingBoard);
        assertThat(events1).isEmpty();

        // when
        final List<Event> events2 = engine.nextMove();

        // then
        assertThat(engine.currentBoard()).isEqualTo(BoardBuilder.fromASCII(
                "....",
                "0.1.",
                "....",
                "...."));
        assertThat(events2).containsExactly(new TankMoved(1, Board.Direction.Right, 1));
    }
}