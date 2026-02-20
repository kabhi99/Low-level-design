package elevator.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Elevator {
    private final String id;
    private int currentFloor;
    private ElevatorState state;
    private final List<Request> currentJobs;
    private final Set<Request> pickedUp;

    public Elevator(String id, int initialFloor) {
        this.id = id;
        this.currentFloor = initialFloor;
        this.state = ElevatorState.IDLE;
        this.currentJobs = new ArrayList<>();
        this.pickedUp = new HashSet<>();
    }

    public void addRequest(Request request) {
        if (state == ElevatorState.MAINTENANCE) {
            throw new IllegalStateException("Elevator " + id + " is in maintenance");
        }
        if (!currentJobs.contains(request)) {
            currentJobs.add(request);
            updateStateAfterAdd();
        }
    }

    private void updateStateAfterAdd() {
        if (state != ElevatorState.IDLE || currentJobs.isEmpty()) {
            return;
        }
        Request next = currentJobs.get(0);
        if (next.getCurrentFloor() > currentFloor) {
            state = ElevatorState.MOVING_UP;
        } else if (next.getCurrentFloor() < currentFloor) {
            state = ElevatorState.MOVING_DOWN;
        } else {
            state = next.getDirection() == Direction.UP ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
        }
    }

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public void openDoor() {
    }

    public void closeDoor() {
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean isAvailable() {
        return state == ElevatorState.IDLE || state == ElevatorState.MOVING_UP || state == ElevatorState.MOVING_DOWN;
    }

    public boolean canService(Request request) {
        if (state == ElevatorState.MAINTENANCE) {
            return false;
        }
        if (state == ElevatorState.IDLE) {
            return true;
        }
        if (state == ElevatorState.MOVING_UP && request.getDirection() == Direction.UP
                && request.getCurrentFloor() >= currentFloor
                && request.getDesiredFloor() >= currentFloor) {
            return true;
        }
        if (state == ElevatorState.MOVING_DOWN && request.getDirection() == Direction.DOWN
                && request.getCurrentFloor() <= currentFloor
                && request.getDesiredFloor() <= currentFloor) {
            return true;
        }
        return false;
    }

    public int distanceTo(Request request) {
        return Math.abs(currentFloor - request.getCurrentFloor());
    }

    public ElevatorState getState() {
        return state;
    }

    public List<Request> getCurrentJobs() {
        return new ArrayList<>(currentJobs);
    }

    public String getId() {
        return id;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void processFloorArrival(int floor) {
        currentJobs.forEach(r -> {
            if (r.getCurrentFloor() == floor) {
                pickedUp.add(r);
            }
        });
        currentJobs.removeIf(r -> r.getDesiredFloor() == floor);
        pickedUp.removeIf(r -> r.getDesiredFloor() == floor);
    }

    public void removeCompletedRequests(int floor) {
        currentJobs.removeIf(r -> r.getDesiredFloor() == floor);
        pickedUp.removeIf(r -> r.getDesiredFloor() == floor);
    }

    public boolean hasPickedUp(Request request) {
        return pickedUp.contains(request);
    }

    public boolean hasJobs() {
        return !currentJobs.isEmpty();
    }

    public Request getNextTarget() {
        if (currentJobs.isEmpty()) {
            return null;
        }
        return currentJobs.get(0);
    }
}
