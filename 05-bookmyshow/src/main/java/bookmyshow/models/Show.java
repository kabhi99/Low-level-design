package bookmyshow.models;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Show {

    private final String id;
    private final Movie movie;
    private final Theater theater;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Map<String, Seat> seats;

    public Show(Movie movie, Theater theater, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = new ConcurrentHashMap<>();
    }

    public Show(String id, Movie movie, Theater theater, LocalDateTime startTime,
                LocalDateTime endTime, Map<String, Seat> seats) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = new ConcurrentHashMap<>(seats);
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Map<String, Seat> getSeats() {
        return Collections.unmodifiableMap(seats);
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }
}
