package main.main;

import main.generator.Generator;

import java.util.ArrayList;

public class StartVariables {
    private static ArrayList<Integer> startValues;


    public static ArrayList<Integer> getStartValues() {
        if (startValues == null){
            startValues = Generator.generateStartValues();
        }
        return startValues;
    }


}
