package pl.edu.agh.ietanks.sandbox.simple;

import pl.edu.agh.ietanks.boards.api.BoardsReader;
import pl.edu.agh.ietanks.boards.model.Board;

import java.util.List;

public class SimpleSandbox {

    private final BotService botService;

    private final BoardsReader boardsReader;

    public SimpleSandbox(BotService botService, BoardsReader boardsReader) {
        this.botService = botService;
        this.boardsReader = boardsReader;
    }

    //
    public void startNewGameplay(int boardId, List<BotId> botIds) {
        Board board = boardsReader.getBoard(boardId);
        List<StringBotRepresentation> bots = botService.fetch(botIds);
    }


}
