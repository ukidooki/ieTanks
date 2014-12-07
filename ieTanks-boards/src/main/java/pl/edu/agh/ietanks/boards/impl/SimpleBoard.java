package pl.edu.agh.ietanks.boards.impl;

import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.boards.model.Field;

import java.util.Arrays;
import java.util.List;

public class SimpleBoard {
    public Board create(int id) {
        int width = 10;
        int height = 10;
        List<Field> obstacles = Arrays.asList(
                new Field(1, 1),
                new Field(1, 8),
                new Field(8, 1),
                new Field(8, 8),
                new Field(3, 3),
                new Field(3, 6),
                new Field(6, 3),
                new Field(6, 6)
        );
        List<Field> startingPoints = Arrays.asList(
                new Field(0, 0),
                new Field(0, 9),
                new Field(9, 0),
                new Field(9, 9)
        );
        return new Board(id, "Simple board", width, height, obstacles, startingPoints);
    }
}
