package pl.edu.agh.ietanks.gameplay.game;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.boards.model.Field;
import pl.edu.agh.ietanks.engine.api.Engine;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.BotId;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;

import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class GameRunnerFactoryTest {

    @Mock
    private GameHistory storage;

    @InjectMocks
    private GameRunnerFactory factory;

    @Test
    public void shouldCreateGameRunner() {
        // given
        BotAlgorithm firstBotAlgorithm = new BotAlgorithm(new BotId("first-bot"), "python");
        BotAlgorithm secondBotAlgorithm = new BotAlgorithm(new BotId("second-bot"), "not-python");
        List<BotAlgorithm> algorithms = Lists.newArrayList(firstBotAlgorithm, secondBotAlgorithm);
        List<Field> startingPoints = Lists.newArrayList(new Field(0, 0), new Field(2, 3));

        Board board = new Board(1, "board", 3, 4, Collections.emptyList(), startingPoints);

        // when
        GameRunner runner = factory.create(board, algorithms, mock(Engine.class));

        // then
        assertThat(runner.getGameParticipants()).isEqualTo(algorithms);
    }
}