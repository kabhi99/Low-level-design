# Designing an ATM System

## Requirements
1. The ATM should support basic operations: check balance, deposit, withdraw, and transfer funds.
2. The ATM should authenticate users via card number and PIN.
3. The ATM should interact with a bank system to validate accounts and process transactions.
4. The ATM should dispense cash in different denominations and handle insufficient funds.
5. The ATM should maintain different states: idle, card inserted, authenticated, transaction in progress.
6. The system should handle concurrent access and ensure transaction atomicity.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/atm/)

## Key Design Decisions
1. **State Pattern**: ATM states (Idle, CardInserted, Authenticated, TransactionInProgress) as separate state classes.
2. **Chain of Responsibility**: Cash dispensing through denomination handlers (2000 → 500 → 200 → 100).
3. **Strategy Pattern**: Different transaction types (Withdraw, Deposit, Transfer, BalanceCheck) implement a common `TransactionStrategy`.

## Design Patterns Used
1. **State Pattern**: Clean state transitions for ATM workflow.
2. **Chain of Responsibility**: Denomination-based cash dispensing chain.
3. **Strategy Pattern**: Pluggable transaction processing strategies.
