package main.building;

import main.elevator.Direction;
import main.elevator.Elevator;
import main.floor.Floor;
import main.generator.Generator;
import main.main.StartVariables;
import main.passenger.Passenger;

import java.util.ArrayList;

public class Building {
    private final Elevator elevator;

    private final int floorsNumber;

    private final ArrayList<Floor> floors;

    public Building(Elevator elevator, int floorsNumber) {
        this.elevator = elevator;
        this.floorsNumber = floorsNumber;
        this.floors = new ArrayList<>();
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setFloorPassengers(){
        for (int i = 0; i < floorsNumber; ++i){
            ArrayList<Passenger> floorPassengers = Generator.generateFloorPassengers(i, floorsNumber, StartVariables.getStartValues().get(i));
            Floor floor = new Floor(floorPassengers);
            floors.add(floor);
        }
    }

    public void setElevatorPassengers() {
       int currentElevatorPassengerNumber = elevator.getElevatorPassengers().size();
       Elevator elevator = this.elevator;
       if (currentElevatorPassengerNumber < Generator.MaxElevatorPassengerNumber && floors.get(elevator.getCurrentFloor()).getPassengers().size() > 0){
            for (int i = 0; i < floors.get(this.elevator.getCurrentFloor()).getPassengers().size(); ++i){
                Passenger passenger = floors.get(elevator.getCurrentFloor()).getPassengers().get(i);
                if((passenger.getTargetFloor() > elevator.getCurrentFloor()
                        && elevator.getDirection() == Direction.UP) ||
                        (passenger.getTargetFloor() < elevator.getCurrentFloor()
                                && elevator.getDirection() == Direction.DOWN)){
                    floors.get(elevator.getCurrentFloor()).getPassengers().remove(i--);
                    elevator.addElevatorPassenger(passenger);
                    ++currentElevatorPassengerNumber;
                }
                if (currentElevatorPassengerNumber == Generator.MaxElevatorPassengerNumber){
                    break;
                }
            }
       }
    }

    public void proceed() {
        floors.get(elevator.getCurrentFloor()).mergeFloorLists(elevator.getCurrentFloor(), getFloorsNumber());
        if (elevator.getDirection() == Direction.UP && elevator.getCurrentFloor() == Generator.MaxFloorsNumber){
            elevator.setDirection(Direction.DOWN);
        } else{
            if (elevator.getDirection() == Direction.DOWN && elevator.getCurrentFloor() == 0){
                elevator.setDirection(Direction.UP);
            }
        }
        elevator.setCurrentFloor(elevator.getCurrentFloor() + (elevator.getDirection() == Direction.UP ? 1 : (-1) ) );
        this.proceedPassengers();
        this.proceedDirection();
        this.setElevatorPassengers();
    }

    private void proceedDirection() {
        if (elevator.getElevatorPassengers().size() == 0){
            int upPassenger = 0;
            int downPassenger = 0;
            for (Passenger passenger : floors.get(elevator.getCurrentFloor()).getPassengers()){
                if (passenger.getTargetFloor() > elevator.getCurrentFloor()){
                    ++upPassenger;
                } else {
                    ++downPassenger;
                }
            }
            if (upPassenger < 1 && downPassenger < 1){
                if (elevator.getCurrentFloor() == Generator.MaxFloorsNumber){
                    elevator.setDirection(Direction.DOWN);
                } else{
                    if (elevator.getCurrentFloor() == 0){
                        elevator.setDirection(Direction.UP);
                    } else {
                        if (upPassenger >= downPassenger){
                            elevator.setDirection(Direction.UP);
                        } else{
                            elevator.setDirection(Direction.DOWN);
                        }
                    }
                }
            }
        }
    }

    private void proceedPassengers() {
        ArrayList<Passenger> currentPassengers = new ArrayList<>();
        ArrayList<Passenger> newElevatorPassengers = new ArrayList<>();
        for (Passenger passenger : elevator.getElevatorPassengers()){
            if (passenger.getTargetFloor() == elevator.getCurrentFloor()){
                currentPassengers.add(passenger);
            } else {
                newElevatorPassengers.add(passenger);
            }
        }
        elevator.setElevatorPassengers(newElevatorPassengers);
        floors.get(elevator.getCurrentFloor()).setArrivedPassengers(currentPassengers);
    }


}
