package elevator;

import elevator.models.Elevator;
import elevator.models.ElevatorState;
import elevator.models.Request;
import elevator.service.ElevatorController;
import elevator.strategy.NearestElevatorStrategy;

public class Main {

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(new NearestElevatorStrategy());

        controller.addElevator(new Elevator("E1", 0));
        controller.addElevator(new Elevator("E2", 2));
        controller.addElevator(new Elevator("E3", 5));

        System.out.println("=== Initial State ===");
        printElevators(controller);

        controller.requestElevator(new Request(1, 4));
        controller.requestElevator(new Request(3, 0));

        System.out.println("\n=== After Requests (1->4, 3->0) ===");
        printElevators(controller);

        System.out.println("\n=== Simulation (10 steps) ===");
        for (int i = 0; i < 10; i++) {
            controller.step();
            System.out.println("Step " + (i + 1) + ":");
            printElevators(controller);
        }
    }

    private static void printElevators(ElevatorController controller) {
        controller.getElevators().forEach(e -> {
            String state = e.getState() == ElevatorState.IDLE ? "IDLE" :
                    e.getState() == ElevatorState.MOVING_UP ? "UP" :
                            e.getState() == ElevatorState.MOVING_DOWN ? "DOWN" : "MAINT";
            System.out.printf("  %s: floor %d, state=%s, jobs=%d%n",
                    e.getId(), e.getCurrentFloor(), state, e.getCurrentJobs().size());
        });
    }
}
