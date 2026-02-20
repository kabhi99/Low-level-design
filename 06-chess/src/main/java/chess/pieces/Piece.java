package chess.pieces;

import chess.models.Color;
import chess.models.Position;
import chess.service.Board;

public abstract class Piece {
    protected Color color;
    protected Position position;
    protected boolean killed;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.killed = false;
    }

    public abstract boolean canMove(Board board, Position start, Position end);

    public abstract char getSymbol();

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    protected char getUnicodeSymbol(char whiteSymbol, char blackSymbol) {
        return color == Color.WHITE ? whiteSymbol : blackSymbol;
    }
}
