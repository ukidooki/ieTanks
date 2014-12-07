package pl.edu.agh.ietanks.gameplay.game.rest;

import pl.edu.agh.ietanks.boards.model.Board;
import pl.edu.agh.ietanks.boards.model.Obstacle;

import java.util.List;

public class MapPojo {
    private final int width;
    private final int height;
    private final List<Obstacle> obstacles;

    public MapPojo(Board boardModel) {
        this.width = boardModel.getWidth();
        this.height = boardModel.getHeight();
        this.obstacles = boardModel.getObstacles();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
