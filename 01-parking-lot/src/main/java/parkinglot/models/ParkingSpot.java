package parkinglot.models;

import java.util.concurrent.atomic.AtomicReference;

public class ParkingSpot {
    private final String spotId;
    private final ParkingSpotType spotType;
    private final AtomicReference<Vehicle> vehicle;

    public ParkingSpot(String spotId, ParkingSpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.vehicle = new AtomicReference<>(null);
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    public boolean isAvailable() {
        return vehicle.get() == null;
    }

    public Vehicle getVehicle() {
        return vehicle.get();
    }

    public boolean occupy(Vehicle v) {
        return vehicle.compareAndSet(null, v);
    }

    public Vehicle release() {
        return vehicle.getAndSet(null);
    }
}
