package elevator.service;

import elevator.models.Elevator;
import elevator.models.ElevatorState;
import elevator.models.Request;
import elevator.strategy.ElevatorSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;
    private ElevatorSelectionStrategy selectionStrategy;

    public ElevatorController(ElevatorSelectionStrategy selectionStrategy) {
        this.elevators = new ArrayList<>();
        this.selectionStrategy = selectionStrategy;
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public Elevator requestElevator(Request request) {
        Elevator elevator = selectionStrategy.selectElevator(elevators, request);
        if (elevator != null) {
            elevator.addRequest(request);
            return elevator;
        }
        return null;
    }

    public void step() {
        for (Elevator elevator : elevators) {
            if (elevator.getState() == ElevatorState.MAINTENANCE) {
                continue;
            }

            Request target = elevator.getNextTarget();
            if (target == null) {
                elevator.setState(ElevatorState.IDLE);
                continue;
            }

            int currentFloor = elevator.getCurrentFloor();
            int nextStop = getNextStopFloor(elevator, target);

            if (nextStop == currentFloor) {
                elevator.openDoor();
                elevator.processFloorArrival(currentFloor);
                elevator.closeDoor();
                elevator.setState(ElevatorState.IDLE);
                if (elevator.hasJobs()) {
                    updateStateFromNextTarget(elevator);
                }
                continue;
            }

            if (nextStop > currentFloor) {
                elevator.setState(ElevatorState.MOVING_UP);
                elevator.moveUp();
            } else {
                elevator.setState(ElevatorState.MOVING_DOWN);
                elevator.moveDown();
            }

            currentFloor = elevator.getCurrentFloor();
            elevator.processFloorArrival(currentFloor);
            if (!elevator.hasJobs()) {
                elevator.setState(ElevatorState.IDLE);
            } else {
                updateStateFromNextTarget(elevator);
            }
        }
    }

    private void updateStateFromNextTarget(Elevator elevator) {
        Request next = elevator.getNextTarget();
        if (next == null) {
            elevator.setState(ElevatorState.IDLE);
            return;
        }
        int current = elevator.getCurrentFloor();
        int nextStop = getNextStopFloor(elevator, next);
        elevator.setState(nextStop > current ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN);
    }

    private int getNextStopFloor(Elevator elevator, Request target) {
        if (elevator.hasPickedUp(target)) {
            return target.getDesiredFloor();
        }
        return target.getCurrentFloor();
    }

    public List<Elevator> getElevators() {
        return new ArrayList<>(elevators);
    }
}
