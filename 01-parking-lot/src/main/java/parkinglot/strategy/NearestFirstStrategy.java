package parkinglot.strategy;

import parkinglot.models.ParkingFloor;
import parkinglot.models.ParkingSpot;
import parkinglot.models.VehicleType;
import parkinglot.service.ParkingLot;

public class NearestFirstStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        var floors = parkingLot.getFloors();
        for (ParkingFloor floor : floors) {
            var availableSpots = floor.getAvailableSpotsByType(vehicleType.getCompatibleSpotType());
            if (!availableSpots.isEmpty()) {
                return availableSpots.get(0);
            }
        }
        return null;
    }
}
