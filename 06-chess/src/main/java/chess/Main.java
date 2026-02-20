package chess;

import chess.models.Color;
import chess.models.Player;
import chess.service.Game;

public class Main {

    public static void main(String[] args) {
        Player whitePlayer = new Player("Alice", Color.WHITE);
        Player blackPlayer = new Player("Bob", Color.BLACK);
        Game game = new Game(whitePlayer, blackPlayer);

        System.out.println("=== Initial Board ===");
        game.getBoard().printBoard();

        System.out.println("Move 1: e2 -> e4 (White pawn)");
        if (game.makeMove(6, 4, 4, 4)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("Move 2: e7 -> e5 (Black pawn)");
        if (game.makeMove(1, 4, 3, 4)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("Move 3: g1 -> f3 (White knight)");
        if (game.makeMove(7, 6, 5, 5)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("Move 4: b8 -> c6 (Black knight)");
        if (game.makeMove(0, 1, 2, 2)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("Move 5: f1 -> c4 (White bishop)");
        if (game.makeMove(7, 5, 4, 2)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("Move 6: f8 -> c5 (Black bishop)");
        if (game.makeMove(0, 5, 3, 2)) {
            game.switchTurn();
        }
        game.getBoard().printBoard();

        System.out.println("=== Game State ===");
        System.out.println("Current player: " + game.getCurrentPlayer().getName());
        System.out.println("Status: " + game.getStatus());
    }
}
