package snakeladder.service;

import snakeladder.models.Board;
import snakeladder.models.Ladder;
import snakeladder.models.Snake;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class GameManager {
    private static volatile GameManager instance;
    private final Map<String, SnakeAndLadderGame> activeGames = new ConcurrentHashMap<>();
    private final AtomicLong gameCounter = new AtomicLong(0);

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) {
                    instance = new GameManager();
                }
            }
        }
        return instance;
    }

    public SnakeAndLadderGame createGame(List<Snake> snakes, List<Ladder> ladders, List<String> playerNames) {
        Board board = new Board(100, snakes, ladders);
        SnakeAndLadderGame game = new SnakeAndLadderGame(board, playerNames);
        String gameId = "game-" + System.currentTimeMillis() + "-" + gameCounter.incrementAndGet();
        activeGames.put(gameId, game);
        new Thread(() -> {
            game.play();
            activeGames.remove(gameId);
        }).start();
        return game;
    }
}
