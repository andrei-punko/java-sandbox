package by.andd3dfx.aspects;

import by.andd3dfx.aspects.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomUserAspectTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testAspects() {
        User user = new User();
        user.setName("Andrei");
        user.setAge(37);
        user.printHello();

        assertThat("Wrong output", outContent.toString(), is(EXPECTED_CONSOLE_LOG));
    }

    String EXPECTED_CONSOLE_LOG = "Action before setName() method call\r\n" +
            "Action around setAge() method call - first part\r\n" +
            "Parameter is 37\r\n" +
            "Action around setAge() method call - second part\r\n" +
            "Action before printHello() method call\r\n" +
            "Hello, Andrei! You are 37 years old!\r\n" +
            "Action after printHello() method call\r\n";
}
