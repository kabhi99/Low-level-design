package tictactoe.service;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Player;
import tictactoe.models.Symbol;
import tictactoe.strategy.WinStrategy;

public class GameService {
    private final Board board;
    private final Player[] players;
    private final WinStrategy winStrategy;
    private int currentPlayerIndex;

    public GameService(Board board, Player[] players, WinStrategy winStrategy) {
        this.board = board;
        this.players = players;
        this.winStrategy = winStrategy;
        this.currentPlayerIndex = 0;
    }

    public MoveResult makeMove(int row, int col) {
        Player currentPlayer = getCurrentPlayer();
        if (!board.placeSymbol(row, col, currentPlayer.getSymbol())) {
            return MoveResult.INVALID;
        }
        Move move = new Move(row, col, currentPlayer);
        if (winStrategy.checkWin(board, currentPlayer.getSymbol(), row, col)) {
            return MoveResult.WIN;
        }
        if (board.isFull()) {
            return MoveResult.DRAW;
        }
        switchTurn();
        return MoveResult.VALID;
    }

    public void switchTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    public void resetGame() {
        board.reset();
        currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Board getBoard() {
        return board;
    }
}
