package chess.service;

import chess.models.Color;
import chess.models.Move;
import chess.models.Position;
import chess.pieces.*;

public class Board {
    private static final int SIZE = 8;
    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int col = 0; col < SIZE; col++) {
            grid[1][col] = new Pawn(Color.BLACK, new Position(1, col));
            grid[6][col] = new Pawn(Color.WHITE, new Position(6, col));
        }

        grid[0][0] = new Rook(Color.BLACK, new Position(0, 0));
        grid[0][7] = new Rook(Color.BLACK, new Position(0, 7));
        grid[0][1] = new Knight(Color.BLACK, new Position(0, 1));
        grid[0][6] = new Knight(Color.BLACK, new Position(0, 6));
        grid[0][2] = new Bishop(Color.BLACK, new Position(0, 2));
        grid[0][5] = new Bishop(Color.BLACK, new Position(0, 5));
        grid[0][3] = new Queen(Color.BLACK, new Position(0, 3));
        grid[0][4] = new King(Color.BLACK, new Position(0, 4));

        grid[7][0] = new Rook(Color.WHITE, new Position(7, 0));
        grid[7][7] = new Rook(Color.WHITE, new Position(7, 7));
        grid[7][1] = new Knight(Color.WHITE, new Position(7, 1));
        grid[7][6] = new Knight(Color.WHITE, new Position(7, 6));
        grid[7][2] = new Bishop(Color.WHITE, new Position(7, 2));
        grid[7][5] = new Bishop(Color.WHITE, new Position(7, 5));
        grid[7][3] = new Queen(Color.WHITE, new Position(7, 3));
        grid[7][4] = new King(Color.WHITE, new Position(7, 4));
    }

    public Piece getPiece(int row, int col) {
        if (!Position.isValid(row, col)) return null;
        return grid[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        if (Position.isValid(row, col)) {
            grid[row][col] = piece;
            if (piece != null) {
                piece.setPosition(new Position(row, col));
            }
        }
    }

    public boolean movePiece(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Piece piece = move.getPiece();

        if (!isValidMove(move)) {
            return false;
        }

        Piece captured = grid[end.getRow()][end.getCol()];
        if (captured != null) {
            captured.setKilled(true);
        }

        grid[start.getRow()][start.getCol()] = null;
        grid[end.getRow()][end.getCol()] = piece;
        piece.setPosition(end);

        return true;
    }

    public boolean isValidMove(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Piece piece = move.getPiece();

        if (piece == null || piece.isKilled()) return false;
        if (!start.isValid() || !end.isValid()) return false;
        if (start.equals(end)) return false;

        Piece atStart = getPiece(start.getRow(), start.getCol());
        if (atStart != piece) return false;

        return piece.canMove(this, start, end);
    }

    public boolean isPathClear(Position start, Position end) {
        int rowStep = Integer.compare(end.getRow(), start.getRow());
        int colStep = Integer.compare(end.getCol(), start.getCol());
        int row = start.getRow() + rowStep;
        int col = start.getCol() + colStep;

        while (row != end.getRow() || col != end.getCol()) {
            if (getPiece(row, col) != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("\n  a b c d e f g h");
        System.out.println(" +-----------------+");
        for (int row = 0; row < SIZE; row++) {
            System.out.print((8 - row) + "|");
            for (int col = 0; col < SIZE; col++) {
                Piece piece = grid[row][col];
                if (piece == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(piece.getSymbol());
                }
                System.out.print(" ");
            }
            System.out.println("|" + (8 - row));
        }
        System.out.println(" +-----------------+");
        System.out.println("  a b c d e f g h\n");
    }

    public int getSize() {
        return SIZE;
    }
}
