package main.elevator;

import main.passenger.Passenger;

import java.util.ArrayList;

public class Elevator {
    private int currentFloor;

    private Direction direction;
    private ArrayList<Passenger> elevatorPassengers;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.elevatorPassengers = new ArrayList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ArrayList<Passenger> getElevatorPassengers() {
        return elevatorPassengers;
    }

    public void setElevatorPassengers(ArrayList<Passenger> elevatorPassengers) {
        this.elevatorPassengers = elevatorPassengers;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addElevatorPassenger(Passenger passenger){
        this.elevatorPassengers.add(passenger);
    }

}
