package by.andd3dfx.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BClassTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void checkCallOrder() {
        BClass bClass = new BClass();
        bClass.showValue();

        assertThat("Wrong console output", outContent.toString().replaceAll("\r\n", "\n"), is(""
            + "Static block of class A\n"
            + "Static block of class B\n"
            + "Usual block of class A, 100\n"
            + "Class A constructor, 100\n"
            + "Call of class B method, 0\n"
            + "Usual block of class B, 1000\n"
            + "Class B constructor, 1000\n"
            + "Call of class B method, 1000\n"
            + "Call of class B method, 1000\n"));
    }
}
