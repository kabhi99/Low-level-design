package snakeladder.models;

import java.util.List;

public class Board {
    private final int size;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    public Board() {
        this(100);
    }

    public Board(int size) {
        this(size, List.of(), List.of());
    }

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        if (size < 1) {
            throw new IllegalArgumentException("Board size must be at least 1");
        }
        this.size = size;
        this.snakes = snakes != null ? List.copyOf(snakes) : List.of();
        this.ladders = ladders != null ? List.copyOf(ladders) : List.of();
    }

    public int getSize() {
        return size;
    }

    public int getNewPosition(int position) {
        int newPosition = position;
        for (Snake snake : snakes) {
            if (snake.getHead() == newPosition) {
                newPosition = snake.getTail();
                break;
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == newPosition) {
                newPosition = ladder.getEnd();
                break;
            }
        }
        return newPosition;
    }
}
