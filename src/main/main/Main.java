package main.main;


import main.building.Building;
import main.elevator.Direction;
import main.elevator.Elevator;
import main.floor.Floor;
import main.passenger.Passenger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String s = "";
        int floorsNumber = StartVariables.getStartValues().size(), currentStep = 0;
        Elevator elevator = new Elevator(0);
        elevator.setDirection(Direction.UP);
        Building building = new Building(elevator, floorsNumber);
        building.setFloorPassengers();
        printStartInfo(building);
        building.setElevatorPassengers();
        while(!s.equals("stop")){
            Scanner sc = new Scanner(System.in);
            s = sc.nextLine();
            building.proceed();
            printStepInfo(currentStep++, building);
        }
        System.out.println("Program Finished!");
    }

    private static void printStartInfo(Building building){
        System.out.println("******Start Generated Info******");
        System.out.println("Floor Number: " + StartVariables.getStartValues().size());
        System.out.println("Floors Info");
        int floorNumber = 0;
        for (Floor floor : building.getFloors()){
            System.out.println("Floor Number: " + (floorNumber++) + "  Floor Passenger Number: " + floor.getPassengers().size());
            for (Passenger passenger : floor.getPassengers()){
                System.out.print(passenger.getTargetFloor() + " ");
            }
            System.out.println();
        }
        System.out.println("*************************");
    }
    public static void printStepInfo(int step, Building building){
        System.out.println("**************************");
        System.out.println("______STEP: " + step+"______");
        System.out.println("Current floor: " + building.getElevator().getCurrentFloor());
        System.out.println("Current Direction: " + building.getElevator().getDirection().toString());
        System.out.println("Current Elevator Passenger Number: " + building.getElevator().getElevatorPassengers().size());
        System.out.print("Current Elevator Passengers' Target Floor: ");
        for (Passenger passenger : building.getElevator().getElevatorPassengers()){
            System.out.print(passenger.getTargetFloor() + " ");
        }
        System.out.println();
        System.out.print("Current Floor Passengers' Target Floor: ");
        for (Passenger passenger : building.getFloors().get(building.getElevator().getCurrentFloor()).getPassengers()){
            System.out.print(passenger.getTargetFloor() + " ");
        }
        System.out.println();
        System.out.println("Leaving Passengers Number: "
                + building.getFloors().get(building.getElevator().getCurrentFloor()).getArrivedPassengers().size());
        }
}
