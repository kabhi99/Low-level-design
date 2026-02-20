# Designing a Vending Machine

## Requirements
1. The vending machine should support multiple products with different prices and quantities.
2. The machine should accept coins and notes of different denominations.
3. The machine should dispense the selected product and return change if applicable.
4. The machine should track inventory and prevent selection of out-of-stock products.
5. The machine should handle various states: idle, collecting money, dispensing product, returning change.
6. The machine should be thread-safe to handle concurrent interactions.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/vendingmachine/)

## Key Design Decisions
1. **State Pattern**: Model the vending machine states (Idle, HasMoney, Dispensing) as separate classes implementing a common `VendingMachineState` interface.
2. **Strategy Pattern**: Pluggable change-making algorithm to compute optimal coin return.
3. **Singleton Pattern**: Single vending machine instance managing its own inventory.

## Design Patterns Used
1. **State Pattern**: Each machine state encapsulates behavior for that state, transitions handled cleanly.
2. **Strategy Pattern**: Change calculation algorithm can be swapped (greedy, DP-based).
3. **Singleton Pattern**: Ensures a single vending machine instance.
