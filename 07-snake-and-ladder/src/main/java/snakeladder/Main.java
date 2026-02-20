package snakeladder;

import snakeladder.models.Board;
import snakeladder.models.Ladder;
import snakeladder.models.Snake;
import snakeladder.service.GameManager;
import snakeladder.service.SnakeAndLadderGame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Snake> snakes = List.of(
                new Snake(62, 19),
                new Snake(97, 79),
                new Snake(54, 34),
                new Snake(17, 7)
        );
        List<Ladder> ladders = List.of(
                new Ladder(2, 38),
                new Ladder(9, 31),
                new Ladder(28, 84),
                new Ladder(51, 67),
                new Ladder(21, 42)
        );

        SnakeAndLadderGame game = new SnakeAndLadderGame(
                new Board(100, snakes, ladders),
                List.of("Alice", "Bob", "Charlie")
        );
        game.play();

        System.out.println("--- Concurrent games via GameManager ---");
        GameManager manager = GameManager.getInstance();
        manager.createGame(snakes, ladders, List.of("Player1", "Player2"));
        manager.createGame(snakes, ladders, List.of("Dave", "Eve"));
    }
}
