package parkinglot;

import parkinglot.models.ParkingFloor;
import parkinglot.models.ParkingSpot;
import parkinglot.models.ParkingSpotType;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.models.VehicleType;
import parkinglot.service.ParkingLot;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = ParkingLot.getInstance();

        List<ParkingSpot> floor1Spots = Arrays.asList(
                new ParkingSpot("F1-S1", ParkingSpotType.SMALL),
                new ParkingSpot("F1-S2", ParkingSpotType.SMALL),
                new ParkingSpot("F1-M1", ParkingSpotType.MEDIUM),
                new ParkingSpot("F1-M2", ParkingSpotType.MEDIUM),
                new ParkingSpot("F1-L1", ParkingSpotType.LARGE)
        );
        lot.addFloor(new ParkingFloor(1, floor1Spots));

        List<ParkingSpot> floor2Spots = Arrays.asList(
                new ParkingSpot("F2-S1", ParkingSpotType.SMALL),
                new ParkingSpot("F2-M1", ParkingSpotType.MEDIUM),
                new ParkingSpot("F2-L1", ParkingSpotType.LARGE)
        );
        lot.addFloor(new ParkingFloor(2, floor2Spots));

        Vehicle bike = new Vehicle("BK-001", VehicleType.BIKE);
        Vehicle car = new Vehicle("CR-002", VehicleType.CAR);
        Vehicle truck = new Vehicle("TR-003", VehicleType.TRUCK);

        Ticket t1 = lot.parkVehicle(bike);
        System.out.println("Parked bike: " + t1.getTicketId() + " at " + t1.getParkingSpot().getSpotId());

        Ticket t2 = lot.parkVehicle(car);
        System.out.println("Parked car: " + t2.getTicketId() + " at " + t2.getParkingSpot().getSpotId());

        Ticket t3 = lot.parkVehicle(truck);
        System.out.println("Parked truck: " + t3.getTicketId() + " at " + t3.getParkingSpot().getSpotId());

        Thread.sleep(2100);

        long fee1 = lot.unparkVehicle(t1);
        System.out.println("Unparked bike. Fee: $" + fee1);

        long fee2 = lot.unparkVehicle(t2);
        System.out.println("Unparked car. Fee: $" + fee2);

        long fee3 = lot.unparkVehicle(t3);
        System.out.println("Unparked truck. Fee: $" + fee3);
    }
}
