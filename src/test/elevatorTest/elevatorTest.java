package test.elevatorTest;

import main.elevator.Direction;
import main.elevator.Elevator;
import main.passenger.Passenger;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class elevatorTest {

    @Test
    public void elevatorConstructorTest(){
        Elevator elevator = new Elevator(1);
        assertThat(elevator.getCurrentFloor()).isEqualTo(1);
        elevator.setDirection(Direction.UP);
        assertThat(elevator.getDirection()).isEqualTo(Direction.UP);
        assertThat(elevator.getElevatorPassengers()).isNotNull();
        assertThat(elevator.getElevatorPassengers().size()).isEqualTo(0);
        ArrayList<Passenger> pass = new ArrayList<>();
        pass.add(new Passenger(2));
        pass.add(new Passenger(3));
        pass.add(new Passenger(4));
        elevator.setElevatorPassengers(pass);
        assertThat(elevator.getElevatorPassengers()).isSameAs(pass);
    }

    @Test
    public void addElevatorPassengerTest(){
        Elevator elevator = new Elevator(1);
        elevator.addElevatorPassenger(new Passenger(2));
        assertThat(elevator.getElevatorPassengers().size()).isEqualTo(1);
        assertThat(elevator.getElevatorPassengers().get(0).getTargetFloor()).isEqualTo(2);
    }
}
