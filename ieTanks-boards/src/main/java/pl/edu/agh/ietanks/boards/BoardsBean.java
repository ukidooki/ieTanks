package pl.edu.agh.ietanks.boards;

import pl.edu.agh.ietanks.boards.api.BoardsReader;
import pl.edu.agh.ietanks.boards.model.Board;

import java.util.ArrayList;
import java.util.List;


public class BoardsBean implements BoardsReader {
    private List<Board> boards = new ArrayList<Board>();

    @Override
    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public Board getBoard(int boardId) {
        return boards.get(boardId);
    }
}
