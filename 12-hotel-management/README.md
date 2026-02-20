# Designing a Hotel Management System

## Requirements
1. The system should manage multiple hotels, each with different room types (Standard, Deluxe, Suite).
2. Guests should be able to search for available rooms by date range, room type, and price.
3. The system should handle room reservations with check-in and check-out functionality.
4. The system should support payment processing and generate invoices.
5. The system should prevent double-booking and handle cancellations with refund policies.
6. The system should handle concurrent reservation requests for the same room.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/hotel/)

## Key Design Decisions
1. **Singleton Pattern**: Single `HotelManager` coordinating all hotels and reservations.
2. **Strategy Pattern**: Pluggable pricing strategy (seasonal, dynamic, flat-rate).
3. **State Pattern**: Room states (Available, Reserved, Occupied, Maintenance) with clean transitions.

## Design Patterns Used
1. **Singleton Pattern**: Centralized hotel management.
2. **Strategy Pattern**: Flexible pricing algorithms.
3. **State Pattern**: Room lifecycle state management.
4. **Builder Pattern**: Complex reservation object construction.
