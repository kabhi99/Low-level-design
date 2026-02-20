package parkinglot.strategy;

import parkinglot.models.ParkingSpot;
import parkinglot.models.VehicleType;
import parkinglot.service.ParkingLot;

public interface ParkingStrategy {
    ParkingSpot findSpot(ParkingLot parkingLot, VehicleType vehicleType);
}
