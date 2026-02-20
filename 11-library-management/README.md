# Designing a Library Management System

## Requirements
1. The system should allow librarians to add, update, and remove books from the catalog.
2. Members should be able to search for books by title, author, or ISBN.
3. Members should be able to borrow and return books, with a maximum borrowing limit.
4. The system should track due dates and calculate fines for late returns.
5. The system should handle book reservations when a book is currently checked out.
6. The system should send notifications for due dates and available reservations.
7. The system should support concurrent borrowing and returning operations.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/library/)

## Key Design Decisions
1. **Singleton Pattern**: Single `Library` instance managing the catalog and members.
2. **Observer Pattern**: Notify members when reserved books become available.
3. **Strategy Pattern**: Pluggable search strategy (by title, author, ISBN, or combined).

## Design Patterns Used
1. **Singleton Pattern**: Single library instance managing all state.
2. **Observer Pattern**: Notification system for reservations and due dates.
3. **Strategy Pattern**: Flexible book search algorithms.
