package pl.edu.agh.ietanks.gameplay.game.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import pl.edu.agh.ietanks.gameplay.game.api.Game;
import pl.edu.agh.ietanks.gameplay.game.api.GameHistory;
import pl.edu.agh.ietanks.gameplay.game.api.GameId;

import java.util.List;

@RestController
public class GameHistoryService {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private static class GameNotFoundException extends RuntimeException {
        public GameNotFoundException(GameId gameId){
            super("Game with id not found in history: " + gameId);
        }
    }

    @Autowired
    private GameHistory gameHistory;

    @RequestMapping("/api/game")
    public List<GameId> getGameIds() {
        return gameHistory.getFinishedGamesIds();
    }

    @RequestMapping("/api/game/{gameId}")
    public GamePojo getGame(@ModelAttribute("gameId") GameId gameId) {
        Game storedGame = gameHistory.getGame(gameId);
        if(storedGame == null) throw new GameNotFoundException(gameId);

        return  new GamePojo(storedGame);
    }
}
