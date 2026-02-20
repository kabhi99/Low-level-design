package snakeladder.models;

import java.util.Random;

public class Dice {
    private final int numDice;
    private final Random random;

    public Dice() {
        this(1);
    }

    public Dice(int numDice) {
        if (numDice < 1) {
            throw new IllegalArgumentException("Number of dice must be at least 1");
        }
        this.numDice = numDice;
        this.random = new Random();
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < numDice; i++) {
            sum += random.nextInt(6) + 1;
        }
        return sum;
    }
}
