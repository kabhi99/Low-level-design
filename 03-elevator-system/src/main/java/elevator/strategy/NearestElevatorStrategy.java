package elevator.strategy;

import elevator.models.Elevator;
import elevator.models.Request;

import java.util.Comparator;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        return elevators.stream()
                .filter(e -> e.canService(request))
                .min(Comparator.comparingInt(e -> e.distanceTo(request)))
                .orElse(null);
    }
}
