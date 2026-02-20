package parkinglot.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>(spots);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getSpots() {
        return Collections.unmodifiableList(spots);
    }

    public List<ParkingSpot> getAvailableSpotsByType(ParkingSpotType spotType) {
        return spots.stream()
                .filter(s -> s.isAvailable() && s.getSpotType() == spotType)
                .collect(Collectors.toList());
    }
}
