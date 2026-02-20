package chess.pieces;

import chess.models.Color;
import chess.models.Position;
import chess.service.Board;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position start, Position end) {
        if (!end.isValid()) return false;

        Piece destinationPiece = board.getPiece(end.getRow(), end.getCol());
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getCol() - start.getCol();

        if (color == Color.WHITE) {
            if (rowDiff >= 0) return false;
            return validateWhitePawnMove(board, start, end, destinationPiece, rowDiff, colDiff);
        } else {
            if (rowDiff <= 0) return false;
            return validateBlackPawnMove(board, start, end, destinationPiece, rowDiff, colDiff);
        }
    }

    private boolean validateWhitePawnMove(Board board, Position start, Position end,
                                         Piece destinationPiece, int rowDiff, int colDiff) {
        if (colDiff == 0) {
            if (destinationPiece != null) return false;
            if (rowDiff == -1) return true;
            if (rowDiff == -2 && start.getRow() == 6) {
                return board.getPiece(5, start.getCol()) == null && destinationPiece == null;
            }
            return false;
        } else if (Math.abs(colDiff) == 1) {
            return destinationPiece != null && destinationPiece.getColor() != color;
        }
        return false;
    }

    private boolean validateBlackPawnMove(Board board, Position start, Position end,
                                         Piece destinationPiece, int rowDiff, int colDiff) {
        if (colDiff == 0) {
            if (destinationPiece != null) return false;
            if (rowDiff == 1) return true;
            if (rowDiff == 2 && start.getRow() == 1) {
                return board.getPiece(2, start.getCol()) == null && destinationPiece == null;
            }
            return false;
        } else if (Math.abs(colDiff) == 1) {
            return destinationPiece != null && destinationPiece.getColor() != color;
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return getUnicodeSymbol('\u2659', '\u265F');
    }
}
