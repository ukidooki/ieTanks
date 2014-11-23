package pl.edu.agh.ietanks.sandbox.simple;

import com.google.common.collect.Lists;
import pl.edu.agh.ietanks.boards.api.BoardsReader;
import pl.edu.agh.ietanks.boards.model.Board;

import java.util.Collections;

import static org.mockito.Mockito.*;


public class SimpleSandboxTest {

    private BotService botService = mock(BotService.class);

    private BoardsReader boardsReader = mock(BoardsReader.class);

    private SimpleSandbox simpleSandbox = new SimpleSandbox(botService, boardsReader);

    public void shouldStartGameplay() {
        // given
        int boardId = 5;
        Board sampleBoard = new Board(5, "some-board", 1, 1, Collections.emptyList());
        BotId botId = new BotId("some-bot");
        StringBotRepresentation stringBotRepresentation = new StringBotRepresentation(botId, "some-bot-code");

        // when
        when(botService.fetch(botId)).thenReturn(stringBotRepresentation);
        when(boardsReader.getBoard(boardId)).thenReturn(sampleBoard);
        simpleSandbox.startNewGameplay(boardId, Lists.newArrayList(botId));

        // then

    }
}