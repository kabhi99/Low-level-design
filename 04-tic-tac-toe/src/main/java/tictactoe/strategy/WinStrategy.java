package tictactoe.strategy;

import tictactoe.models.Board;
import tictactoe.models.Symbol;

public interface WinStrategy {
    boolean checkWin(Board board, Symbol symbol, int lastRow, int lastCol);
}
