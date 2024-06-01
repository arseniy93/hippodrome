import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class HippodromeTest {

    Hippodrome hippodrome;
    List<Horse> list;

    @Test
    public void testConstructorNull() {

        IllegalArgumentException exception=Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });

        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void testConstructorEmptyList() {
        list= Collections.EMPTY_LIST;

        IllegalArgumentException exception=Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(list);
        });
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorse(){
         list= new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("Horse "+"№ "+i,i+1));
        }
        hippodrome=new Hippodrome(list);
        assertArrayEquals(list.toArray(),hippodrome.getHorses().toArray());
    }
    @Test

    public void move() {
       list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(Mockito.mock(Horse.class));
        }
       hippodrome = new Hippodrome(list);
       hippodrome.move();
       list.forEach(x-> Mockito.verify(x, Mockito.times(1)).move());

    }
    @Test
    public void testGetWinner(){
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Horse("Horse"+" № "+i,i,i));
            System.out.println(list.get(i).getDistance());
        }
        hippodrome = new Hippodrome(list);
        assertSame(list.get(4),hippodrome.getWinner());


    }

}
