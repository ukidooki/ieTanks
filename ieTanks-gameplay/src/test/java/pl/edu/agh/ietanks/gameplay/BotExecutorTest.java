package pl.edu.agh.ietanks.gameplay;

import org.junit.Test;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.simple.SimpleBoard;
import pl.edu.agh.ietanks.engine.simple.actions.Move;
import pl.edu.agh.ietanks.gameplay.bot.BotExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static org.fest.assertions.Assertions.assertThat;

public class BotExecutorTest {
    private String loadResourceFromFile(String filename) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + filename)));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }

    @Test
    public void should_load_resource_correctly() throws IOException {
        //given
        String filename = "TestBot.py";

        //when
        String result = loadResourceFromFile(filename);

        //then
        assertThat(result).isNotEmpty();
    }

    @Test
    public void should_return_simple_up_move() throws IOException {
        //given
        String pythonAlgorithm = loadResourceFromFile("TestBot.py");
        BotExecutor underTest = new BotExecutor(pythonAlgorithm);
        Board board = new SimpleBoard(3, 3, new HashMap<>());

        //when
        Action resultAction = underTest.performAction(board);

        //then
        assertThat(resultAction).isInstanceOf(Action.class);
        assertThat((Action) resultAction).isEqualTo(new Move(Board.Direction.Right, 1));
    }
}
