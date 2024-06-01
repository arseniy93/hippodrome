import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22000,unit= TimeUnit.MILLISECONDS)
    @Disabled
    public void  testMain(){
        Main main=new Main();

    }
}
