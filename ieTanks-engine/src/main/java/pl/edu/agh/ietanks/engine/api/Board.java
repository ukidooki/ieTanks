package pl.edu.agh.ietanks.engine.api;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Optional;

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
     * Finds tank on given position on map.
     */
    Integer findTank(Position position);
    
    /**
     * Finds all missiles.
     */
    Collection<Missile> findMissiles();
    
    /**
     * Finds missiles on given position on map.
     */
    Collection<Missile> findMissiles(Position position);

    /**
     * Checks whether the given position is within the board.
     */
    boolean isWithin(Position position);

    /**
     * Checks whether the tank may take a given position
     */
    boolean isAccessibleForTank(Position position);

    enum Direction {
        Right, Left, Up, Down, Up_Right, Up_Left, Down_Right, Down_Left
    }
}