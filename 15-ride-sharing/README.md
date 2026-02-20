# Designing a Ride-Sharing Service (Uber)

## Requirements
1. The system should allow riders to request rides by specifying pickup and drop-off locations.
2. The system should match riders with nearby available drivers.
3. Drivers should be able to accept or reject ride requests.
4. The system should calculate fare based on distance, time, and ride type (economy, premium).
5. The system should track ride status (Requested, Accepted, InProgress, Completed, Cancelled).
6. The system should support real-time location tracking of drivers.
7. The system should handle concurrent ride requests and driver matching.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/ridesharing/)

## Key Design Decisions
1. **Strategy Pattern**: Driver matching strategy (NearestDriver, HighestRatedDriver) and fare calculation strategy (DistanceBased, TimeBased, SurgePricing).
2. **Observer Pattern**: Notify riders and drivers on ride status changes.
3. **State Pattern**: Ride lifecycle with state transitions.
4. **Singleton Pattern**: Single `RideService` coordinating all ride operations.

## Design Patterns Used
1. **Strategy Pattern**: Pluggable matching and pricing algorithms.
2. **Observer Pattern**: Real-time notifications.
3. **State Pattern**: Ride state management.
4. **Command Pattern**: Encapsulate ride actions (request, accept, start, complete, cancel).
