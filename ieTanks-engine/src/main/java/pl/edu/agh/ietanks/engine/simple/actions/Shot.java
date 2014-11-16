package pl.edu.agh.ietanks.engine.simple.actions;

import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Board.Direction;

/**
 * Represents shot request from the bot to the game engine.
 */
public class Shot implements Action {
	
	private Board.Direction direction;
	private int speed;
	
	public Shot(Direction direction, int speed) {
		super();
		this.direction = direction;
		this.speed = speed;
	}

	public Board.Direction getDirection() {
		return direction;
	}

	public int getSpeed() {
		return speed;
	}

}
