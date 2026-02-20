package parkinglot.service;

import parkinglot.models.ParkingFloor;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.strategy.NearestFirstStrategy;
import parkinglot.strategy.ParkingStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private static final int FEE_PER_HOUR = 10;

    private static volatile ParkingLot instance;
    private static final Object lock = new Object();

    private final List<ParkingFloor> floors;
    private volatile ParkingStrategy strategy;

    private ParkingLot() {
        this.floors = new ArrayList<>();
        this.strategy = new NearestFirstStrategy();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        synchronized (floors) {
            floors.add(floor);
        }
    }

    public List<ParkingFloor> getFloors() {
        synchronized (floors) {
            return Collections.unmodifiableList(new ArrayList<>(floors));
        }
    }

    public void setStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        while (true) {
            ParkingSpot spot = strategy.findSpot(this, vehicle.getVehicleType());
            if (spot == null) {
                throw new IllegalStateException("No available spot for vehicle type: " + vehicle.getVehicleType());
            }
            if (spot.occupy(vehicle)) {
                return new Ticket(UUID.randomUUID(), vehicle, spot, LocalDateTime.now());
            }
        }
    }

    public long unparkVehicle(Ticket ticket) {
        Vehicle released = ticket.getParkingSpot().release();
        if (released == null) {
            throw new IllegalStateException("Spot was already vacant");
        }
        long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), LocalDateTime.now());
        if (hours < 1) {
            hours = 1;
        }
        return hours * FEE_PER_HOUR;
    }
}
