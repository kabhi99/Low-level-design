# Designing an Online Shopping System (Amazon)

## Requirements
1. The system should allow sellers to list products with details (name, description, price, quantity).
2. Users should be able to search for products by name, category, or price range.
3. Users should be able to add products to a shopping cart and place orders.
4. The system should handle inventory management and prevent overselling.
5. The system should support multiple payment methods (credit card, UPI, wallet).
6. The system should track order status (Placed, Shipped, Delivered, Cancelled).
7. The system should handle concurrent purchases of the same product.

## UML Class Diagram

*To be added after implementation*

## Implementations
#### [Java Implementation](src/main/java/shopping/)

## Key Design Decisions
1. **Strategy Pattern**: Payment processing via different payment strategies (CreditCard, UPI, Wallet).
2. **Observer Pattern**: Notify users on order status changes.
3. **State Pattern**: Order lifecycle states with valid transitions.
4. **Singleton Pattern**: Single `ShoppingService` managing catalog and orders.

## Design Patterns Used
1. **Strategy Pattern**: Pluggable payment methods.
2. **Observer Pattern**: Order status notifications.
3. **State Pattern**: Order state management.
4. **Builder Pattern**: Complex order and product object construction.
