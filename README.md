# Low Level Design — Java

Most frequently asked LLD problems in top tech companies, implemented in clean Java with proper OOP, SOLID principles, and design patterns.

> Reference: [awesome-low-level-design](https://github.com/ashishps1/awesome-low-level-design) by Ashish Pratap Singh

## Tier 1 Problems

| # | Problem | Patterns | Key Classes | UML |
|---|---------|----------|-------------|-----|
| 01 | [Parking Lot](01-parking-lot/) | Singleton, Strategy | ParkingLot, ParkingSpot, Vehicle, Ticket | [View](01-parking-lot/README.md) |
| 02 | [LRU Cache](02-lru-cache/) | HashMap + DLL | LRUCache, Node, DoublyLinkedList | [View](02-lru-cache/README.md) |
| 03 | [Elevator System](03-elevator-system/) | Strategy, State | Elevator, ElevatorController, Request | [View](03-elevator-system/README.md) |
| 04 | [Tic-Tac-Toe](04-tic-tac-toe/) | Strategy | Board, GameService, WinStrategy | [View](04-tic-tac-toe/README.md) |
| 05 | [BookMyShow](05-bookmyshow/) | Singleton, Concurrency | BookingService, Show, Seat, Booking | [View](05-bookmyshow/README.md) |
| 06 | [Chess Game](06-chess/) | Polymorphism | Piece (abstract), Board, Game | [View](06-chess/README.md) |
| 07 | [Snake & Ladder](07-snake-and-ladder/) | Singleton, Composition | Board, Snake, Ladder, GameManager | [View](07-snake-and-ladder/README.md) |
| 08 | [Logging Framework](08-logging-framework/) | Singleton, Strategy | Logger, LogAppender, LoggerConfig | [View](08-logging-framework/README.md) |

## Project Structure

```
Low level design/
├── README.md
├── 01-parking-lot/          # Singleton + Strategy Pattern
│   ├── README.md            # Requirements + Mermaid UML
│   └── src/main/java/parkinglot/
│       ├── models/          # VehicleType, Vehicle, ParkingSpot, ParkingFloor, Ticket
│       ├── strategy/        # ParkingStrategy, NearestFirstStrategy
│       ├── service/         # ParkingLot (Singleton)
│       └── Main.java
├── 02-lru-cache/            # HashMap + Doubly Linked List
│   ├── README.md
│   └── src/main/java/lrucache/
│       ├── Node.java, DoublyLinkedList.java, LRUCache.java
│       └── Main.java
├── 03-elevator-system/      # Strategy + State Pattern
│   ├── README.md
│   └── src/main/java/elevator/
│       ├── models/          # Direction, ElevatorState, Request, Elevator
│       ├── strategy/        # ElevatorSelectionStrategy, NearestElevatorStrategy
│       ├── service/         # ElevatorController
│       └── Main.java
├── 04-tic-tac-toe/          # Strategy Pattern + O(1) Win Check
│   ├── README.md
│   └── src/main/java/tictactoe/
│       ├── models/          # Symbol, Player, Board, Move
│       ├── strategy/        # WinStrategy, DefaultWinStrategy
│       ├── service/         # GameService, MoveResult
│       └── Main.java
├── 05-bookmyshow/           # Concurrency + Seat Locking
│   ├── README.md
│   └── src/main/java/bookmyshow/
│       ├── models/          # Movie, Theater, Show, Seat, Booking, User
│       ├── service/         # BookingService (Singleton)
│       └── Main.java
├── 06-chess/                # Polymorphism + Move Validation
│   ├── README.md
│   └── src/main/java/chess/
│       ├── models/          # Color, Position, Move, Player, GameStatus
│       ├── pieces/          # Piece (abstract), King, Queen, Rook, Bishop, Knight, Pawn
│       ├── service/         # Board, Game
│       └── Main.java
├── 07-snake-and-ladder/     # Composition + Singleton GameManager
│   ├── README.md
│   └── src/main/java/snakeladder/
│       ├── models/          # Snake, Ladder, Player, Dice, Board
│       ├── service/         # SnakeAndLadderGame, GameManager
│       └── Main.java
└── 08-logging-framework/    # Singleton + Strategy (Appenders)
    ├── README.md
    └── src/main/java/logger/
        ├── models/          # LogLevel, LogMessage
        ├── appender/        # LogAppender, ConsoleAppender, FileAppender, DatabaseAppender
        ├── service/         # Logger (Singleton), LoggerConfig
        └── Main.java
```

## How to Run Any Problem

```bash
cd <problem-directory>
mkdir -p out
javac -sourcepath src/main/java -d out src/main/java/<package>/Main.java
java -cp out <package>.Main
```

Example:
```bash
cd 01-parking-lot
mkdir -p out
javac -sourcepath src/main/java -d out src/main/java/parkinglot/Main.java
java -cp out parkinglot.Main
```

## Key Design Patterns Used

| Pattern | Where |
|---------|-------|
| **Singleton** | ParkingLot, BookingService, GameManager, Logger |
| **Strategy** | ParkingStrategy, ElevatorSelectionStrategy, WinStrategy, LogAppender |
| **State** | ElevatorState, SeatStatus, BookingStatus, GameStatus |
| **Polymorphism** | Chess pieces (Piece → King, Queen, Rook, Bishop, Knight, Pawn) |
| **Composition** | Board contains Snakes + Ladders, ParkingFloor contains ParkingSpots |

## SOLID Principles Applied

- **S** — Each class has a single responsibility (e.g., `Board` only manages grid, `Game` manages flow)
- **O** — New strategies/appenders can be added without modifying existing code
- **L** — All chess pieces are substitutable for `Piece` base class
- **I** — Focused interfaces (`ParkingStrategy`, `WinStrategy`, `LogAppender`)
- **D** — Services depend on abstractions (strategy interfaces), not concrete implementations
