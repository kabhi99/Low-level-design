# Designing a Car Rental System

## Requirements
1. The system should manage a fleet of vehicles of different types (sedan, SUV, luxury).
2. Customers should be able to search for available vehicles by type, date range, and location.
3. Customers should be able to make, modify, and cancel reservations.
4. The system should handle pricing based on vehicle type, rental duration, and insurance options.
5. The system should track vehicle status (Available, Reserved, Rented, UnderMaintenance).
6. The system should support multiple rental locations with vehicle transfers.
7. The system should handle concurrent reservation requests.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/carrental/)

## Key Design Decisions
1. **Strategy Pattern**: Pricing strategy based on vehicle type and duration (daily, weekly, monthly rates).
2. **State Pattern**: Vehicle states with valid transitions (Available → Reserved → Rented → Available).
3. **Singleton Pattern**: Single `RentalService` managing fleet and reservations.
4. **Decorator Pattern**: Optional add-ons (insurance, GPS, child seat) decorating base rental price.

## Design Patterns Used
1. **Strategy Pattern**: Pluggable pricing algorithms.
2. **State Pattern**: Vehicle lifecycle management.
3. **Singleton Pattern**: Centralized rental management.
4. **Decorator Pattern**: Composable rental add-ons.
