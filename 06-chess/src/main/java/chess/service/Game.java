package chess.service;

import chess.models.*;
import chess.pieces.Piece;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentTurn;
    private GameStatus status;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.players = new Player[]{whitePlayer, blackPlayer};
        this.currentTurn = 0;
        this.status = GameStatus.ACTIVE;
    }

    public boolean makeMove(int startRow, int startCol, int endRow, int endCol) {
        if (status != GameStatus.ACTIVE && status != GameStatus.CHECK) {
            return false;
        }

        Piece piece = board.getPiece(startRow, startCol);
        if (piece == null) {
            return false;
        }

        Player currentPlayer = players[currentTurn];
        if (piece.getColor() != currentPlayer.getColor()) {
            return false;
        }

        Position start = new Position(startRow, startCol);
        Position end = new Position(endRow, endCol);

        Piece destinationPiece = board.getPiece(endRow, endCol);
        if (destinationPiece != null && destinationPiece.getColor() == piece.getColor()) {
            return false;
        }

        Move move = new Move(piece, start, end);
        if (!board.isValidMove(move)) {
            return false;
        }

        return board.movePiece(move);
    }

    public void switchTurn() {
        currentTurn = 1 - currentTurn;
    }

    public boolean isGameOver() {
        return status == GameStatus.CHECKMATE || status == GameStatus.STALEMATE || status == GameStatus.RESIGNED;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentTurn];
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
