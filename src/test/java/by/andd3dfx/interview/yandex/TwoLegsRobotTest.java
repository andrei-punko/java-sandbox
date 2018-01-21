package by.andd3dfx.interview.yandex;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TwoLegsRobotTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void main() throws InterruptedException {
        TwoLegsRobot.main(new String[]{});

        checkLogs(systemOutRule.getLog());
    }

    private void checkLogs(String log) {
        String[] lines = log.split("\r\n");

        boolean leftExpected = lines[0].startsWith("left");
        for (String line : lines) {
            String expectedString = leftExpected ? "left" : "right";
            assertThat("Wrong string ordering", line.startsWith(expectedString), is(true));
            leftExpected = !leftExpected;
        }
    }
}
