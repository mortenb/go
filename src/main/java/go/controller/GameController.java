package go.controller;

import go.domain.Game;
import go.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by moby on 05/04/15.
 */
@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public String index() {
        return "Hello";
    }

    @RequestMapping("/game/{id}")
    public Game viewGame(@PathVariable Long id) {
        return gameService.viewGame(id);
    }

    @RequestMapping("/game/new")
    public Game createGame() {
        Long gameId = gameService.createGame(9);
        return gameService.viewGame(gameId);
    }
}
