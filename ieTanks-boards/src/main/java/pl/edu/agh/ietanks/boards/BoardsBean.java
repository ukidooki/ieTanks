package pl.edu.agh.ietanks.boards;

import org.springframework.stereotype.Service;
import pl.edu.agh.ietanks.boards.api.BoardsReader;
import pl.edu.agh.ietanks.boards.impl.SimpleBoard;
import pl.edu.agh.ietanks.boards.model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardsBean implements BoardsReader {
    private Map<Integer, Board> boards = new HashMap<>();

    public BoardsBean() {
        Board simpleBoard = new SimpleBoard().create(1);
        boards.put(1, simpleBoard);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards.values());
    }

    @Override
    public Board getBoard(int boardId) {
        return boards.get(boardId);
    }

}
