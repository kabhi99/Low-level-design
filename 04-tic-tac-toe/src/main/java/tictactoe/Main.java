package tictactoe;

import tictactoe.models.Board;
import tictactoe.models.Player;
import tictactoe.models.Symbol;
import tictactoe.service.GameService;
import tictactoe.service.MoveResult;
import tictactoe.strategy.DefaultWinStrategy;
import tictactoe.strategy.WinStrategy;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3);
        Player[] players = {
            new Player("Alice", Symbol.X),
            new Player("Bob", Symbol.O)
        };
        WinStrategy winStrategy = new DefaultWinStrategy();
        GameService gameService = new GameService(board, players, winStrategy);

        int[][] moves = {{0, 0}, {1, 1}, {0, 1}, {2, 0}, {0, 2}};

        System.out.println("Tic-Tac-Toe Game Demo\n");
        System.out.println("Initial board:");
        board.printBoard();
        System.out.println();

        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            Player current = gameService.getCurrentPlayer();
            System.out.println(current.getName() + " (" + current.getSymbol() + ") plays at (" + row + ", " + col + ")");

            MoveResult result = gameService.makeMove(row, col);
            System.out.println("Result: " + result);
            System.out.println("Board state:");
            board.printBoard();

            if (result == MoveResult.WIN) {
                System.out.println("\n" + current.getName() + " wins!");
                break;
            }
            if (result == MoveResult.DRAW) {
                System.out.println("\nDraw!");
                break;
            }
            if (result == MoveResult.INVALID) {
                System.out.println("\nInvalid move. Skipping.");
            }
            System.out.println();
        }
    }
}
