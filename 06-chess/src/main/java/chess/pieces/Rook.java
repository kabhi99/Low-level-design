package chess.pieces;

import chess.models.Color;
import chess.models.Position;
import chess.service.Board;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position start, Position end) {
        if (!end.isValid()) return false;

        Piece destinationPiece = board.getPiece(end.getRow(), end.getCol());
        if (destinationPiece != null && destinationPiece.getColor() == color) {
            return false;
        }

        boolean isVertical = start.getCol() == end.getCol() && start.getRow() != end.getRow();
        boolean isHorizontal = start.getRow() == end.getRow() && start.getCol() != end.getCol();

        if (!isVertical && !isHorizontal) {
            return false;
        }

        return board.isPathClear(start, end);
    }

    @Override
    public char getSymbol() {
        return getUnicodeSymbol('\u2656', '\u265C');
    }
}
