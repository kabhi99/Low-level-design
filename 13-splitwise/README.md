# Designing Splitwise

## Requirements
1. The system should allow users to create groups and add members.
2. Users should be able to add expenses with different split strategies (equal, exact, percentage).
3. The system should track balances between all pairs of users.
4. The system should simplify debts to minimize the number of transactions needed to settle up.
5. The system should support individual expenses (between two users) and group expenses.
6. The system should provide a summary of balances for each user.
7. The system should handle concurrent expense additions.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/splitwise/)

## Key Design Decisions
1. **Strategy Pattern**: Different split strategies (EqualSplit, ExactSplit, PercentageSplit) implementing a common `SplitStrategy` interface.
2. **Singleton Pattern**: Single `ExpenseManager` tracking all users, groups, and balances.
3. **Graph-based simplification**: Minimize transactions using net balance calculation and greedy settlement.

## Design Patterns Used
1. **Strategy Pattern**: Pluggable expense splitting algorithms.
2. **Singleton Pattern**: Centralized expense and balance management.
3. **Observer Pattern**: Notify users when new expenses are added or debts are settled.
