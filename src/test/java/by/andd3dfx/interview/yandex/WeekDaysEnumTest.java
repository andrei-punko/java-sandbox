package by.andd3dfx.interview.yandex;

import org.junit.Test;

import static by.andd3dfx.interview.yandex.WeekDaysEnum.MONDAY;
import static by.andd3dfx.interview.yandex.WeekDaysEnum.SUNDAY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WeekDaysEnumTest {

    @Test
    public void values() {
        WeekDaysEnum[] result = WeekDaysEnum.values();

        assertThat("Wrong size", result.length, is(2));

        assertThat("Wrong entity, SUNDAY expected", result[0], is(SUNDAY));
        assertThat("Wrong entity, MONDAY expected", result[1], is(MONDAY));
    }

    @Test
    public void valueOf() {
        WeekDaysEnum result = WeekDaysEnum.valueOf("MONDAY");

        assertThat("Wrong entity, MONDAY expected", result, is(MONDAY));
    }

    @Test
    public void ordinal() {
        assertThat("Ordinal value 0 expected", SUNDAY.ordinal(), is(0));
        assertThat("Ordinal value 1 expected", MONDAY.ordinal(), is(1));
    }

    @Test
    public void name() {
        assertThat("SUNDAY expected", SUNDAY.name(), is("SUNDAY"));
        assertThat("MONDAY expected", MONDAY.name(), is("MONDAY"));
    }
}
