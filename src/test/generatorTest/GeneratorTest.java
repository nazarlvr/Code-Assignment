package test.generatorTest;

import static org.assertj.core.api.Assertions.*;
import main.generator.Generator;
import main.passenger.Passenger;
import org.junit.Test;

import java.util.ArrayList;

public class GeneratorTest {


    @Test
    public void generateStartValues() {
        ArrayList<Integer> testAr = Generator.generateStartValues();
        assertThat(testAr.size()).isLessThanOrEqualTo(Generator.MaxFloorsNumber);
        assertThat(testAr.size()).isGreaterThanOrEqualTo(Generator.MinFloorsNumber);
        for (int i: testAr){
            assertThat(i).isGreaterThanOrEqualTo(0);
            assertThat(i).isLessThanOrEqualTo(Generator.MaxFloorsPassengerNumber);
        }
    }

    @Test
    public void generateTargetFloorTest(){
        ArrayList<Integer> testAr = Generator.generateStartValues();
        int n = testAr.size();
        int floorNumber = 2;
        int generatedTargetFloor = Generator.generateTargetFloor(floorNumber, n);
        assertThat(generatedTargetFloor).isNotEqualTo(floorNumber);
        assertThat(generatedTargetFloor).isLessThan(n);
        assertThat(generatedTargetFloor).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void generateFloorPassengers(){
        ArrayList<Integer> testAr = Generator.generateStartValues();
        int n = testAr.size();
        int floorNumber = 2;
        ArrayList<Passenger> passTest = Generator.generateFloorPassengers(floorNumber, n, testAr.get(floorNumber));
        assertThat(passTest.size()).isEqualTo(testAr.get(floorNumber));
    }


}