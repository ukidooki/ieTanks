package pl.edu.agh.ietanks.gameplay.game;

import com.google.common.collect.Lists;
import org.junit.Test;
import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.gameplay.game.api.BotAlgorithm;
import pl.edu.agh.ietanks.gameplay.game.api.BotId;
import pl.edu.agh.ietanks.gameplay.game.innerapi.GameHistoryStorage;

import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GameRunnerFactoryTest {

    private GameHistoryStorage storage = mock(GameHistoryStorage.class);

    private GameRunnerFactory factory = new GameRunnerFactory(storage);

    @Test
    public void shouldCreateGameRunner() {
        // given
        BotAlgorithm firstBotAlgorithm = new BotAlgorithm(new BotId("first-bot"), "python");
        BotAlgorithm secondBotAlgorithm = new BotAlgorithm(new BotId("second-bot"), "not-python");
        List<BotAlgorithm> algorithms = Lists.newArrayList(firstBotAlgorithm, secondBotAlgorithm);

        Board board = new Board(1, "board", 3, 4, Collections.emptyList());

        // when
        GameRunner runner = factory.create(board, algorithms);

        // then
        assertThat(runner.getGameParticipants()).isEqualTo(algorithms);
    }
}