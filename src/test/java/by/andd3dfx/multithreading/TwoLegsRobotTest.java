package by.andd3dfx.multithreading;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TwoLegsRobotTest {

    private final String LEFT = "left";
    private final String RIGHT = "right";
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
    public void main() throws InterruptedException {
        TwoLegsRobot.main(new String[]{});

        checkLogs(outContent.toString());
    }

    private void checkLogs(String log) {
        String[] lines = log.split("\r\n");

        Boolean leftExpected = null;
        for (String line : lines) {
            if (!line.startsWith(LEFT) && !line.startsWith(RIGHT)) {
                continue;
            }
            if (leftExpected == null) {
                leftExpected = line.startsWith(RIGHT);
                continue;
            }
            String expectedString = leftExpected ? LEFT : RIGHT;
            assertThat("Expected " + expectedString + ", but actual is " + line, line.startsWith(expectedString), is(true));
            leftExpected = !leftExpected;
        }
    }
}
