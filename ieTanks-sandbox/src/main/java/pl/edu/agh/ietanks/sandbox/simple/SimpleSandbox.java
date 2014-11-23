package pl.edu.agh.ietanks.sandbox.simple;

import pl.edu.agh.ietanks.boards.api.BoardsReader;

import java.util.List;

public class SimpleSandbox {

    private final BotService botService;

    private final BoardsReader boardsReader;

    public SimpleSandbox(BotService botService, BoardsReader boardsReader) {
        this.botService = botService;
        this.boardsReader = boardsReader;
    }

    public void startNewGameplay(int boardId, List<String> botIds) {
        // TODO - after https://jira.iisg.agh.edu.pl/browse/IETANKS-66 add integration with gameplay module API
    }


}
