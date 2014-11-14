package pl.edu.agh.ietanks.engine.api;

import com.google.common.base.Optional;

import java.util.List;

/**
 * Board state interface for engine purposes.
 */
public interface Board {
    /**
     * Returns all tanks (their identifiers) that are still on board.
     */
    List<Integer> tankIds();

    /**
     * Finds tank on map by its id.
     */
    Optional<Position> findTank(int tankId);

    /**
     * Checks whether the given position is within the board.
     */
    boolean isWithin(Position position);

    /**
     * Checks whether the tank may tak a given position
     */
    boolean isAccessibleForTank(Position position);

    enum Direction {
        Right
    }
}