package tictactoe.strategy;

import tictactoe.models.Board;
import tictactoe.models.Symbol;

public class DefaultWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, Symbol symbol, int lastRow, int lastCol) {
        int n = board.getSize();

        if (Math.abs(board.getRowSum(lastRow)) == n) {
            return true;
        }
        if (Math.abs(board.getColSum(lastCol)) == n) {
            return true;
        }
        if (lastRow == lastCol && Math.abs(board.getMainDiagSum()) == n) {
            return true;
        }
        if (lastRow + lastCol == n - 1 && Math.abs(board.getAntiDiagSum()) == n) {
            return true;
        }
        return false;
    }
}
