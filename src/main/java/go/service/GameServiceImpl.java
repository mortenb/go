package go.service;

import go.domain.Game;
import go.domain.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by moby on 03/10/15.
 */

@Service
public class GameServiceImpl implements GameService {


    @Autowired
    GameRepository gameRepository;

    @Override
    public Long createGame(int boardSize) {
        Game g =  gameRepository.save(new Game(boardSize));
        if (g != null) {
            return g.getId();
        }
        return null;
    }

    @Override
    public Game viewGame(Long gameId) {
        return gameRepository.findOne(gameId);
    }

    @Override
    public int play(Long gameId, String playerId, int x, int y) {
        Game g = viewGame(gameId);
        int result = 0;
        if (g != null) {
            result = g.play(playerId, x, y);
            gameRepository.save(g);
        }
        return result;
    }
}
