package tekiela.greetings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GreeterTest {

    @Test
    public void itGreetsUser() {
        //Arrange // Given
        String name = "Kuba";
        Greeter greeter = new Greeter();

        //Act // When
        String message = greeter.hello(name);

        //Assert // Then
        assertEquals("Hello Kuba", message);
    }
}
