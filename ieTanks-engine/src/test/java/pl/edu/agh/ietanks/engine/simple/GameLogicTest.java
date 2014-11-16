package pl.edu.agh.ietanks.engine.simple;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Optional;

import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.engine.api.events.Event;
import pl.edu.agh.ietanks.engine.api.events.TankMoved;
import pl.edu.agh.ietanks.engine.simple.actions.Move;
import pl.edu.agh.ietanks.engine.simple.actions.Shot;
import pl.edu.agh.ietanks.engine.testutils.BoardBuilder;

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
        final List<Event> events = logic.tryApplyAction(new Move(Board.Direction.Right, 1), 0);

        // then
        assertThat(logic.board()).isEqualTo(BoardBuilder.fromASCII(
                "....",
                "..0.",
                "....",
                "...."));

        assertThat(events).containsExactly(new TankMoved(0, Board.Direction.Right, 1));
    }
    
    @Test
    public void shouldCreateMissile() throws Exception {
        //given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                "....");

        GameLogic logic = new GameLogic(board);

        // when
        final List<Event> events = logic.tryApplyAction(new Shot(Board.Direction.Down, 1), 0);

        // then
        assertThat(logic.board().findMissiles()).hasSize(1);
        assertThat(logic.board().findMissiles().iterator().next().position())
        		.isEqualTo(new Position(2, 1));
        assertThat(events).hasSize(1);
    }
    
    @Test
    public void shouldMissileBeMoved() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                "....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down_Right, 1), 0);
        logic.moveMissiles();
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(1);
        assertThat(logic.board().findMissiles().iterator().next().position())
        		.isEqualTo(new Position(3, 3));
        
    }
    
    @Test
    public void shouldMissilesBeDestroyed() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "0....",
                ".....",
                ".....",
                ".....",
                "....1");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down_Right, 1), 0);
        logic.tryApplyAction(new Shot(Board.Direction.Up_Left, 1), 1);
        logic.moveMissiles();
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        
    }
    
    @Test
    public void shouldMissilesBeDestroyed2() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "0....",
                ".....",
                ".....",
                "...1.",
                ".....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down_Right, 1), 0);
        logic.moveMissiles();
        logic.tryApplyAction(new Shot(Board.Direction.Up_Left, 1), 1);
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        
    }
    
    @Test
    public void shouldMissileBeRemoved() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                "....",
                "....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Left, 1), 0);
        logic.moveMissiles();
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        
    }
    
    @Test
    public void shouldMissileAndTankBeRemoved() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                ".1..",
                "....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down, 1), 0);
        logic.tryApplyAction(new Move(Board.Direction.Up, 1), 1);
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        assertThat(logic.board().findTank(1)).isEqualTo(Optional.absent());
    }
    
    @Test
    public void shouldMissileAndTankBeRemoved2() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                "....",
                ".1..",
                "....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down, 1), 0);
        logic.moveMissiles();
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        assertThat(logic.board().findTank(1)).isEqualTo(Optional.absent());
    }
    
    @Test
    public void shouldMissileAndTankBeRemoved3() throws Exception {
    	//given
        MutableBoard board = BoardBuilder.fromASCII(
                "....",
                ".0..",
                ".1..",
                "....",
                "....");

        GameLogic logic = new GameLogic(board);
        
        // when
        logic.tryApplyAction(new Shot(Board.Direction.Down, 1), 0);
        
        // then
        assertThat(logic.board().findMissiles()).hasSize(0);
        assertThat(logic.board().findTank(1)).isEqualTo(Optional.absent());
    }
}