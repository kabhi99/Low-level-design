package bookmyshow.service;

import bookmyshow.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {

    private static volatile BookingService instance;
    private final Map<String, Movie> movies;
    private final Map<String, Theater> theaters;
    private final Map<String, Show> shows;
    private final Map<String, Booking> bookings;

    private BookingService() {
        this.movies = new ConcurrentHashMap<>();
        this.theaters = new ConcurrentHashMap<>();
        this.shows = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public Movie addMovie(String title, String description, int durationMinutes) {
        Movie movie = new Movie(title, description, durationMinutes);
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Theater addTheater(String name, String location) {
        Theater theater = new Theater(name, location);
        theaters.put(theater.getId(), theater);
        return theater;
    }

    public Show addShow(Movie movie, Theater theater, LocalDateTime startTime, LocalDateTime endTime) {
        Show show = new Show(movie, theater, startTime, endTime);
        theater.addShow(show);
        shows.put(show.getId(), show);
        return show;
    }

    public void addSeatsToShow(Show show, int rows, int seatsPerRow) {
        String[] rowLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int r = 0; r < rows; r++) {
            SeatType type = r < 2 ? SeatType.VIP : r < 5 ? SeatType.PREMIUM : SeatType.NORMAL;
            double price = type == SeatType.VIP ? 500 : type == SeatType.PREMIUM ? 300 : 150;
            for (int c = 1; c <= seatsPerRow; c++) {
                Seat seat = new Seat(rowLabels[r], c, type, price);
                show.addSeat(seat);
            }
        }
    }

    public Booking bookTickets(User user, Show show, List<String> seatIds) {
        if (user == null || show == null || seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("User, show and seat IDs are required");
        }
        synchronized (show) {
            List<Seat> seatsToBook = new ArrayList<>();
            for (String seatId : seatIds) {
                Seat seat = show.getSeat(seatId);
                if (seat == null) {
                    throw new IllegalArgumentException("Seat not found: " + seatId);
                }
                if (seat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new IllegalStateException("Seat not available: " + seatId);
                }
                seatsToBook.add(seat);
            }
            double totalPrice = seatsToBook.stream().mapToDouble(Seat::getPrice).sum();
            for (Seat seat : seatsToBook) {
                seat.setStatus(SeatStatus.BOOKED);
            }
            Booking booking = new Booking(user, show, seatsToBook, totalPrice);
            bookings.put(booking.getId(), booking);
            return booking;
        }
    }

    public void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Booking cannot be confirmed: " + booking.getStatus());
        }
        booking.setStatus(BookingStatus.CONFIRMED);
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        synchronized (booking.getShow()) {
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                return;
            }
            for (Seat seat : booking.getSeats()) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }
            booking.setStatus(BookingStatus.CANCELLED);
        }
    }

    public Movie getMovie(String id) {
        return movies.get(id);
    }

    public Theater getTheater(String id) {
        return theaters.get(id);
    }

    public Show getShow(String id) {
        return shows.get(id);
    }

    public Booking getBooking(String id) {
        return bookings.get(id);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }

    public List<Theater> getAllTheaters() {
        return new ArrayList<>(theaters.values());
    }
}
