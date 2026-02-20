package bookmyshow.models;

import java.util.UUID;

public class Seat {

    private final String id;
    private final String row;
    private final int column;
    private final SeatType seatType;
    private final double price;
    private volatile SeatStatus status;

    public Seat(String row, int column, SeatType seatType, double price) {
        this.id = UUID.randomUUID().toString();
        this.row = row;
        this.column = column;
        this.seatType = seatType;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat(String id, String row, int column, SeatType seatType, double price, SeatStatus status) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
