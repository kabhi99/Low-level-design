package snakeladder.service;

import snakeladder.models.Board;
import snakeladder.models.Dice;
import snakeladder.models.Player;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private volatile boolean gameOver;

    public SnakeAndLadderGame(Board board, List<String> playerNames) {
        this.board = board;
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        this.dice = new Dice();
        this.gameOver = false;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void play() {
        int currentPlayerIndex = 0;
        while (!gameOver) {
            Player player = players.get(currentPlayerIndex);
            int roll = dice.roll();
            int newPosition = player.getPosition() + roll;

            if (newPosition > board.getSize()) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            newPosition = board.getNewPosition(newPosition);
            player.setPosition(newPosition);

            if (newPosition == board.getSize()) {
                gameOver = true;
                System.out.println(player.getName() + " wins!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}
