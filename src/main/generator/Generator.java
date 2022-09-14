package main.generator;

import main.building.Building;
import main.building.Building;
import main.floor.Floor;
import main.main.StartVariables;
import main.passenger.Passenger;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public static final int MaxFloorsNumber = 20;
    public static final int MinFloorsNumber = 5;
    public static final int MaxFloorsPassengerNumber = 10;
    public static final int MaxElevatorPassengerNumber = 5;

    public static ArrayList<Integer> generateStartValues() {
        Random rnd = new Random();
        int n = rnd.nextInt(MaxFloorsNumber - MinFloorsNumber + 1) + MinFloorsNumber;
        ArrayList<Integer> startArr = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            Random rand = new Random();
            startArr.add(rand.nextInt(MaxFloorsPassengerNumber + 1));
        }
        return startArr;
    }

    public static ArrayList<Passenger> generateFloorPassengers(int floorNumber, int n, int floorPassengerNumber){
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < floorPassengerNumber; ++i){
            int targetFloor = generateTargetFloor(floorNumber, n);
            Passenger passenger = new Passenger(targetFloor);
            passengers.add(passenger);
        }
        return passengers;
    }

    public static int generateTargetFloor(int floorNumber, int n){
        Random rnd = new Random();
        int targetFloor = rnd.nextInt(n);
        if (targetFloor == floorNumber) {
            targetFloor = targetFloor != (n - 1) ? targetFloor + 1 : targetFloor - 1;
        }
        return targetFloor;
    }


}
