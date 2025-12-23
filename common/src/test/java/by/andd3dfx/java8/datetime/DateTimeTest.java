package by.andd3dfx.java8.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DateTimeTest {

    @Test
    public void clock() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
    }

    @Test
    public void zoneId() {
        var ids = ZoneId.getAvailableZoneIds();   // all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Europe/London");
        var zoneRules1 = zone1.getRules();       // ZoneRules[currentStandardOffset=+01:00]
        var zoneRules2 = zone2.getRules();       // ZoneRules[currentStandardOffset=00:00]
    }

    @Test
    public void localTime() {
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Europe/London");

        // Use fixed Instant (same moment in time) to avoid DST-related test instability
        // In winter (January): Berlin (CET) is UTC+1, London (GMT) is UTC+0
        // Same moment in UTC will show different local times in different zones
        Instant fixedInstant = Instant.parse("2024-01-15T12:00:00Z"); // UTC time
        LocalTime time1 = fixedInstant.atZone(zone1).toLocalTime(); // 13:00 in Berlin (UTC+1)
        LocalTime time2 = fixedInstant.atZone(zone2).toLocalTime(); // 12:00 in London (UTC+0)

        var isTime1BeforeTime2 = time1.isBefore(time2);
        assertThat(isTime1BeforeTime2).isFalse();

        long hoursBetween = ChronoUnit.HOURS.between(time1, time2);
        long minutesBetween = ChronoUnit.MINUTES.between(time1, time2);
        log.debug("Time difference: {} hours, {} minutes", hoursBetween, minutesBetween);
        // In winter time (January), Berlin is 1 hour ahead of London = -60 minutes
        assertThat(minutesBetween).isEqualTo(-60L);

        LocalTime late = LocalTime.of(23, 59, 59);
        assertThat(String.valueOf(late)).isEqualTo("23:59:59");

        DateTimeFormatter germanFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);

        LocalTime germanTime = LocalTime.parse("13:37", germanFormatter);
        assertThat(String.valueOf(germanTime)).isEqualTo("13:37");
    }

    @Test
    public void localDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.FRIDAY);

        // Create LocalDate by parsing string:
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        assertThat(String.valueOf(xmas)).isEqualTo("2014-12-24");
    }

    @Test
    public void localDateTime() {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.WEDNESDAY);

        Month month = sylvester.getMonth();
        assertThat(month).isEqualTo(Month.DECEMBER);

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        assertThat(minuteOfDay).isEqualTo(1439);

        // Get an instant:
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);       // Wed Dec 31 23:59:59 CET 2014

        // Formatting:
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("MMM dd, yyyy - HH:mm", Locale.US);

        LocalDateTime parsedLocalDateTime = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String localDateTimeString = formatter.format(parsedLocalDateTime);
        assertThat(localDateTimeString).isEqualTo("Nov 03, 2014 - 07:13");
    }
}
