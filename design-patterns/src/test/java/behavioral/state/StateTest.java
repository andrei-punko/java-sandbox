package behavioral.state;

import org.junit.Test;

/**
 * Используется в тех случаях, когда во время выполнения программы объект должен менять своё поведение в зависимости от
 * своего состояния.
 */
public class StateTest {

    @Test
    public void doAction() {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
