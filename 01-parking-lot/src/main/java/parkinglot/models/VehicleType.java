package parkinglot.models;

public enum VehicleType {
    BIKE(ParkingSpotType.SMALL),
    CAR(ParkingSpotType.MEDIUM),
    TRUCK(ParkingSpotType.LARGE);

    private final ParkingSpotType compatibleSpotType;

    VehicleType(ParkingSpotType compatibleSpotType) {
        this.compatibleSpotType = compatibleSpotType;
    }

    public ParkingSpotType getCompatibleSpotType() {
        return compatibleSpotType;
    }
}
