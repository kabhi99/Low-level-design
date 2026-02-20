package elevator.models;

import java.util.Objects;

public class Request {
    private final int currentFloor;
    private final int desiredFloor;
    private final Direction direction;

    public Request(int currentFloor, int desiredFloor) {
        if (currentFloor == desiredFloor) {
            throw new IllegalArgumentException("Current and desired floor cannot be the same");
        }
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = currentFloor < desiredFloor ? Direction.UP : Direction.DOWN;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return currentFloor == request.currentFloor
                && desiredFloor == request.desiredFloor
                && direction == request.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentFloor, desiredFloor, direction);
    }
}
