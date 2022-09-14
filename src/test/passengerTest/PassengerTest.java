package test.passengerTest;

import static org.assertj.core.api.Assertions.*;
import main.passenger.Passenger;
import org.junit.Test;

public class PassengerTest {

    @Test
    public void PassengerConstructorTest(){
        Passenger passenger = new Passenger(3);
        assertThat(passenger.getTargetFloor()).isEqualTo(3);
        passenger.setTargetFloor(1);
        assertThat(passenger.getTargetFloor()).isEqualTo(1);
    }
}
