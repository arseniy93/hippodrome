import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class HorseTest {
    Horse horse;
    String name="Bura";
    int speed=1;
    int distance=1;

    @Test
    public void testConstuctor() {
        log.error("eroor vethod testConstructor");
        log.info("info vethod testConstructor");
        log.debug("info vethod testConstructor");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1, 1);
        });
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    public void testConstuctoWithSpaceETC(String symbols) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(symbols, 1, 1);
        });
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());

    }

    @Test
    public void testConstuctoWithSecondArgument() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Мурка", -11, 1);
        });
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());

    }

    @Test
    public void testConstuctoWithThirdArgument() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Мурка", 11, -1);
        });
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());

    }
    @Test
    public void testGetNameFirstArgument(){
        horse= new Horse(name,speed,
                distance);
        assertEquals(horse.getName(),name);
    }
    @Test
    public void testGetNameSecondArgument(){
        horse= new Horse(name,speed,
                distance);
        assertEquals(horse.getSpeed(),speed);
    }
    @Test
    public void testGetNameThirdArgument(){
        horse= new Horse(name,speed,
                distance);
        assertEquals(horse.getDistance(),distance);
    }
    @Test
    public void testGetNameThirdArgumentNull(){
        log.debug("testGetnameMethod is null");
        horse= new Horse(name,speed);
        assertEquals(horse.getDistance(),0);
    }

    @Test
    public void moveUsesGetRandom(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse ("Кабыла", 31, 283).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void move (double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse ("Кабыла", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(283+31*random, horse.getDistance());
        }
    }

}
