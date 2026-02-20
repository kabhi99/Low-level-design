# Designing a Snake and Ladder Game

## Requirements
1. The game should be played on a board with numbered cells, typically with 100 cells.
2. The board should have a predefined set of snakes and ladders, connecting certain cells.
3. The game should support multiple players, each represented by a unique game piece.
4. Players should take turns rolling a dice to determine the number of cells to move forward.
5. If a player lands on a cell with the head of a snake, they should slide down to the cell with the tail of the snake.
6. If a player lands on a cell with the base of a ladder, they should climb up to the cell at the top of the ladder.
7. The game should continue until one of the players reaches the final cell on the board.
8. The game should handle multiple game sessions concurrently, allowing different groups of players to play independently.

## UML Class Diagram

```mermaid
classDiagram
    class Snake {
        -int head
        -int tail
        +getHead() int
        +getTail() int
    }

    class Ladder {
        -int start
        -int end
        +getStart() int
        +getEnd() int
    }

    class Player {
        -String name
        -int position
        +getName() String
        +getPosition() int
        +setPosition(int) void
    }

    class Dice {
        -int numDice
        -Random random
        +roll() int
    }

    class Board {
        -int size
        -List~Snake~ snakes
        -List~Ladder~ ladders
        +getSize() int
        +getNewPosition(int) int
    }

    class SnakeAndLadderGame {
        -Board board
        -List~Player~ players
        -Dice dice
        -boolean gameOver
        +addPlayer(String) void
        +play() void
    }

    class GameManager {
        -GameManager instance$
        -Map activeGames
        +getInstance()$ GameManager
        +createGame(List~Snake~, List~Ladder~, List~String~) SnakeAndLadderGame
    }

    Board "1" --> "*" Snake : contains
    Board "1" --> "*" Ladder : contains
    SnakeAndLadderGame --> Board : uses
    SnakeAndLadderGame "1" --> "*" Player : has
    SnakeAndLadderGame --> Dice : uses
    GameManager --> SnakeAndLadderGame : manages
```

## Implementations
#### [Java Implementation](src/main/java/snakeladder/)

## Classes, Interfaces and Enumerations
1. The **Board** class represents the game board with a fixed size (e.g., 100 cells). It contains the positions of snakes and ladders and provides a `getNewPosition` method to determine the final position after encountering a snake or ladder.
2. The **Player** class represents a player in the game, with properties such as name and current position on the board.
3. The **Snake** class represents a snake on the board, with properties for the head (start) and tail (end) positions. The head must be greater than the tail.
4. The **Ladder** class represents a ladder on the board, with properties for the start (base) and end (top) positions. The end must be greater than the start.
5. The **Dice** class represents a dice used in the game, with a configurable number of dice. The `roll` method returns a random value between 1 and 6 per die, summed together.
6. The **SnakeAndLadderGame** class represents a single game session. It initializes the game with a board, a list of players, and a dice. The `play` method handles the game loop, where players take turns rolling the dice and moving their positions on the board. It checks for snakes and ladders and updates the player's position accordingly. If a move would exceed the board size, the player stays in place. The game continues until a player reaches the final position.
7. The **GameManager** class is a Singleton that manages multiple game sessions. It maintains a map of active games and provides a method to create and start new games. Each game runs in a separate thread to allow concurrent game sessions.
8. The **Main** class demonstrates the usage of the game by creating a `GameManager` instance and starting game sessions with different sets of players.

## Design Patterns Used
1. **Singleton Pattern**: `GameManager` ensures a single instance manages all active game sessions.
2. **Composition**: The `Board` is composed of `Snake` and `Ladder` objects, and `SnakeAndLadderGame` is composed of `Board`, `Player`, and `Dice` objects.
3. **Concurrency**: Each game session runs in a separate thread via `GameManager`, enabling multiple concurrent games.
