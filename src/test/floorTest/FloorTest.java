package test.floorTest;

import main.floor.Floor;
import main.passenger.Passenger;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;

public class FloorTest {

    @Test
    public void FloorConstructorTest(){
        ArrayList<Passenger> pass = new ArrayList<>();
        pass.add(new Passenger(2));
        pass.add(new Passenger(3));
        pass.add(new Passenger(4));
        Floor floor = new Floor(pass);
        assertThat(floor.getPassengers()).isSameAs(pass);
        Passenger passenger = new Passenger(5);
        ArrayList<Passenger> passs = new ArrayList<>();
        passs.add(passenger);
        floor.setArrivedPassengers(passs);
        assertThat(floor.getArrivedPassengers()).isSameAs(passs);
    }

    @Test
    public void MergeListsTest(){
        ArrayList<Passenger> pass = new ArrayList<>();
        pass.add(new Passenger(2));
        pass.add(new Passenger(3));
        pass.add(new Passenger(4));
        Floor floor = new Floor(pass);
        floor.getArrivedPassengers().add(new Passenger(5));
        floor.mergeFloorLists(5, 10);
        assertThat(floor.getPassengers().size()).isEqualTo(4);
        assertThat(floor.getPassengers().get(3).getTargetFloor()).isNotEqualTo(5);
    }
}
