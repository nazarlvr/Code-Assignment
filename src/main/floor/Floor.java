package main.floor;

import main.generator.Generator;
import main.passenger.Passenger;

import java.util.ArrayList;

public class Floor {
    private final ArrayList<Passenger> passengers;


    private ArrayList<Passenger> arrivedPassengers;


    public Floor(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
        this.arrivedPassengers = new ArrayList<>();
    }

    public ArrayList<Passenger> getArrivedPassengers() {
        return arrivedPassengers;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setArrivedPassengers(ArrayList<Passenger> arrivedPassengers) {
        this.arrivedPassengers = arrivedPassengers;
    }

    public void mergeFloorLists(int currentFloor, int n){
        for (Passenger passenger : arrivedPassengers){
            int targetFloor = Generator.generateTargetFloor(currentFloor, n);
            passenger.setTargetFloor(targetFloor);
            passengers.add(passenger);
        }
        arrivedPassengers.clear();
    }
}
