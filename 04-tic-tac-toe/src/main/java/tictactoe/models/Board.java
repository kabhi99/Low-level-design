package tictactoe.models;

public class Board {
    private final int size;
    private final Symbol[][] grid;
    private final int[] rowSums;
    private final int[] colSums;
    private int mainDiagSum;
    private int antiDiagSum;
    private int placedCount;

    public Board(int size) {
        this.size = size;
        this.grid = new Symbol[size][size];
        this.rowSums = new int[size];
        this.colSums = new int[size];
        this.mainDiagSum = 0;
        this.antiDiagSum = 0;
        this.placedCount = 0;
    }

    public boolean placeSymbol(int row, int col, Symbol symbol) {
        if (row < 0 || row >= size || col < 0 || col >= size || grid[row][col] != null) {
            return false;
        }
        grid[row][col] = symbol;
        placedCount++;
        int delta = symbol == Symbol.X ? 1 : -1;
        rowSums[row] += delta;
        colSums[col] += delta;
        if (row == col) {
            mainDiagSum += delta;
        }
        if (row + col == size - 1) {
            antiDiagSum += delta;
        }
        return true;
    }

    public boolean isFull() {
        return placedCount == size * size;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    row.append("-");
                } else {
                    row.append(grid[i][j]);
                }
                if (j < size - 1) {
                    row.append(" ");
                }
            }
            System.out.println(row);
        }
    }

    public int getSize() {
        return size;
    }

    public Symbol getSymbol(int row, int col) {
        return grid[row][col];
    }

    public int getRowSum(int row) {
        return rowSums[row];
    }

    public int getColSum(int col) {
        return colSums[col];
    }

    public int getMainDiagSum() {
        return mainDiagSum;
    }

    public int getAntiDiagSum() {
        return antiDiagSum;
    }

    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
            rowSums[i] = 0;
            colSums[i] = 0;
        }
        mainDiagSum = 0;
        antiDiagSum = 0;
        placedCount = 0;
    }
}
