package test.buildingTest;

import main.building.Building;
import main.elevator.Direction;
import main.elevator.Elevator;
import main.main.StartVariables;
import main.passenger.Passenger;
import org.junit.Test;
import test.passengerTest.PassengerTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class BuildingTest {

    @Test
    public void buildingBasicTest(){
        int floorsNumber = StartVariables.getStartValues().size();
        Elevator elevator = new Elevator(0);
        elevator.setDirection(Direction.UP);
        Building building = new Building(elevator, floorsNumber);
        building.setFloorPassengers();
        assertThat(building.getElevator()).isSameAs(elevator);
        assertThat(building.getFloorsNumber()).isEqualTo(StartVariables.getStartValues().size());
    }

    @Test
    public void setFloorPassengersTest(){
        int floorsNumber = StartVariables.getStartValues().size();
        Elevator elevator = new Elevator(0);
        elevator.setDirection(Direction.UP);
        Building building = new Building(elevator, floorsNumber);
        building.setFloorPassengers();
        assertThat(building.getFloors()).isNotNull();
        assertThat(building.getFloors().size()).isEqualTo(StartVariables.getStartValues().size());
        assertThat(building.getFloors().get(2).getPassengers().size()).isEqualTo(StartVariables.getStartValues().get(2));
    }

    @Test
    public void proceedDirectionTest(){
        int floorsNumber = StartVariables.getStartValues().size();
        Elevator elevator = new Elevator(4);
        elevator.setDirection(Direction.UP);
        Building building = new Building(elevator, floorsNumber);
        building.setFloorPassengers();
        ArrayList<Passenger> pas= new ArrayList<>();

        assertThat(building.getElevator().getCurrentFloor()).isEqualTo(4);
        assertThat(building.getElevator().getElevatorPassengers().size()).isEqualTo(0);

        pas.add(new Passenger(2));
        pas.add(new Passenger(3));
        pas.add(new Passenger(3));
        pas.add(new Passenger(5));
        pas.add(new Passenger(5));
        pas.add(new Passenger(5));
        pas.add(new Passenger(5));
        building.getFloors().get(4).getPassengers().clear();
        building.getFloors().get(4).getPassengers().addAll(pas);
        assertThat(building.getFloors().get(4).getPassengers().size()).isEqualTo(7);
        building.proceedDirectionTest();
        assertThat(building.getElevator().getDirection()).isEqualTo(Direction.UP);

        assertThat(building.getElevator().getCurrentFloor()).isEqualTo(4);
        assertThat(building.getElevator().getElevatorPassengers().size()).isEqualTo(0);

        pas.clear();
        pas.add(new Passenger(2));
        pas.add(new Passenger(3));
        pas.add(new Passenger(3));
        pas.add(new Passenger(2));
        pas.add(new Passenger(2));
        pas.add(new Passenger(5));
        pas.add(new Passenger(5));
        building.getFloors().get(4).getPassengers().clear();
        building.getFloors().get(4).getPassengers().addAll(pas);
        assertThat(building.getFloors().get(4).getPassengers().size()).isEqualTo(7);
        building.proceedDirectionTest();
        assertThat(building.getElevator().getDirection()).isEqualTo(Direction.DOWN);
    }

    @Test
    public void setElevatorPassengersTest(){
        int floorsNumber = StartVariables.getStartValues().size();
        Elevator elevator = new Elevator(3);
        elevator.setDirection(Direction.UP);
        Building building = new Building(elevator, floorsNumber);
        building.setFloorPassengers();
        ArrayList<Passenger> pas= new ArrayList<>();
        pas.add(new Passenger(2));
        pas.add(new Passenger(2));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        building.getFloors().get(3).getPassengers().clear();
        building.getFloors().get(3).getPassengers().addAll(pas);
        building.setElevatorPassengers();
        assertThat(building.getElevator().getElevatorPassengers().size()).isEqualTo(5);
        assertThat(building.getFloors().get(3).getPassengers().size()).isEqualTo(3);

        pas.clear();
        pas.add(new Passenger(2));
        pas.add(new Passenger(1));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        pas.add(new Passenger(4));
        building.getFloors().get(3).getPassengers().clear();
        building.getFloors().get(3).getPassengers().addAll(pas);
        building.getElevator().setDirection(Direction.DOWN);
        building.getElevator().setCurrentFloor(3);
        building.getElevator().getElevatorPassengers().clear();
        assertThat(building.getElevator().getElevatorPassengers().size()).isEqualTo(0);
        building.setElevatorPassengers();
        assertThat(building.getElevator().getElevatorPassengers().size()).isEqualTo(2);
        assertThat(building.getFloors().get(3).getPassengers().size()).isEqualTo(5);
    }

}
