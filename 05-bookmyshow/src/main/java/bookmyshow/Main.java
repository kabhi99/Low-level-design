package bookmyshow;

import bookmyshow.models.*;
import bookmyshow.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BookingService service = BookingService.getInstance();

        Movie movie = service.addMovie("Inception", "A mind-bending thriller", 148);
        System.out.println("Added movie: " + movie.getTitle());

        Theater theater = service.addTheater("PVR Cinemas", "Mumbai");
        System.out.println("Added theater: " + theater.getName() + " at " + theater.getLocation());

        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(18).withMinute(0);
        LocalDateTime endTime = startTime.plusMinutes(movie.getDurationMinutes());
        Show show = service.addShow(movie, theater, startTime, endTime);
        service.addSeatsToShow(show, 5, 6);
        System.out.println("Added show at " + startTime + " with " + show.getSeats().size() + " seats");

        User user = new User("John Doe", "john@example.com");
        List<String> seatIds = show.getSeats().values().stream()
                .limit(3)
                .map(Seat::getId)
                .toList();

        Booking booking = service.bookTickets(user, show, seatIds);
        System.out.println("Booking created: " + booking.getId() + ", Total: Rs " + booking.getTotalPrice());

        service.confirmBooking(booking.getId());
        System.out.println("Booking confirmed: " + service.getBooking(booking.getId()).getStatus());

        User user2 = new User("Jane Smith", "jane@example.com");
        List<String> seatIds2 = show.getSeats().values().stream()
                .filter(s -> s.getStatus() == SeatStatus.AVAILABLE)
                .limit(2)
                .map(Seat::getId)
                .toList();
        Booking booking2 = service.bookTickets(user2, show, seatIds2);
        System.out.println("Second booking: " + booking2.getId());

        service.cancelBooking(booking2.getId());
        System.out.println("Second booking cancelled. Seats freed: "
                + show.getSeats().values().stream().filter(s -> s.getStatus() == SeatStatus.AVAILABLE).count());

        System.out.println("\nDemo complete.");
    }
}
