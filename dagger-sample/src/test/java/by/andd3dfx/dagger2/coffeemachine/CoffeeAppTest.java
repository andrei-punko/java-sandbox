package by.andd3dfx.dagger2.coffeemachine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.dagger2.coffeemachine.CoffeeApp.CoffeeShop;
import java.util.stream.Collectors;
import org.junit.Test;

public class CoffeeAppTest {

    private String EXPECTED_LOGS = "~ ~ ~ heating ~ ~ ~,=> => pumping => =>, [_]P coffee! [_]P ,~ ~ ~ heating stopped ~ ~ ~";

    @Test
    public void testCoffeeApp() {
        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        coffeeShop.maker().brew();
        String collectedLogs = coffeeShop.logger().logs().stream().collect(Collectors.joining(","));
        assertThat("Wrong logs", collectedLogs, is(EXPECTED_LOGS));
    }
}