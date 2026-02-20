package chess.pieces;

import chess.models.Color;
import chess.models.Position;
import chess.service.Board;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position start, Position end) {
        if (!end.isValid()) return false;

        Piece destinationPiece = board.getPiece(end.getRow(), end.getCol());
        if (destinationPiece != null && destinationPiece.getColor() == color) {
            return false;
        }

        int rowDiff = Math.abs(end.getRow() - start.getRow());
        int colDiff = Math.abs(end.getCol() - start.getCol());

        if (rowDiff != colDiff) {
            return false;
        }

        return board.isPathClear(start, end);
    }

    @Override
    public char getSymbol() {
        return getUnicodeSymbol('\u2657', '\u265D');
    }
}
