package chess.pieces;

import chess.models.Position;
import chess.service.Board;

public class Queen extends Piece {

    public Queen(chess.models.Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position start, Position end) {
        if (!end.isValid()) return false;

        Piece destinationPiece = board.getPiece(end.getRow(), end.getCol());
        if (destinationPiece != null && destinationPiece.getColor() == color) {
            return false;
        }

        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getCol() - start.getCol();

        boolean isDiagonal = Math.abs(rowDiff) == Math.abs(colDiff);
        boolean isVertical = colDiff == 0 && rowDiff != 0;
        boolean isHorizontal = rowDiff == 0 && colDiff != 0;

        if (!isDiagonal && !isVertical && !isHorizontal) {
            return false;
        }

        return board.isPathClear(start, end);
    }

    @Override
    public char getSymbol() {
        return getUnicodeSymbol('\u2655', '\u265B');
    }
}
