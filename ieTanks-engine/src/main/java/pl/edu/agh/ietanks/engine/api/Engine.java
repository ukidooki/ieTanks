package pl.edu.agh.ietanks.engine.api;

import pl.edu.agh.ietanks.engine.api.events.Event;

import java.util.List;

/**
 * Game engine interface. Need to be initialized first.
 * After that, you call only nextMove() till the end of the game (that is notified by one of the events).
 */
public interface Engine {
    /**
     * Initializes the engine. Needs board and bots definition.
     *
     * @param initialBoard a board state we are starting with
     * @param bots list of bot definitions, capable of performing moves
     */
    void setup(MutableBoard initialBoard, List<Bot> bots);

    /**
     * Performs next move.
     *
     * @return list of events that happened due to the move (e.g. tank moved, missile fired, game finished)
     */
    List<Event> nextMove();

    /**
     * Retrieves current state of the game board.
     */
    Board currentBoard();
}
