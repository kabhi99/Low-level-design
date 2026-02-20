package bookmyshow.models;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Booking {

    private final String id;
    private final User user;
    private final Show show;
    private final List<Seat> seats;
    private final double totalPrice;
    private volatile BookingStatus status;
    private final LocalDateTime bookingTime;

    public Booking(User user, Show show, List<Seat> seats, double totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = Collections.unmodifiableList(seats);
        this.totalPrice = totalPrice;
        this.status = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
    }

    public Booking(String id, User user, Show show, List<Seat> seats, double totalPrice,
                   BookingStatus status, LocalDateTime bookingTime) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = Collections.unmodifiableList(seats);
        this.totalPrice = totalPrice;
        this.status = status;
        this.bookingTime = bookingTime;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}
