package go.service;

import go.domain.Game;

/**
 * Created by moby on 03/10/15.
 */
public interface GameService {

    public Long createGame(int boardSize);

    public Game viewGame(Long gameId);

    public int play(Long gameId, String playerId, int x, int y);
}
