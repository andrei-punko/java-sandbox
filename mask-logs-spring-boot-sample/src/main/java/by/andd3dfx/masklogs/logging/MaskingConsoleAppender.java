package by.andd3dfx.masklogs.logging;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class MaskingConsoleAppender extends AppenderBase<ILoggingEvent> {

    private static final DateTimeFormatter TS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    @Override
    protected void append(ILoggingEvent eventObject) {
        String timestamp = TS_FORMATTER.format(Instant.ofEpochMilli(eventObject.getTimeStamp()));
        String message = LogMaskingSupport.mask(eventObject.getFormattedMessage());
        String line = "%s %-5s [%s] %s - %s%n".formatted(
                timestamp,
                eventObject.getLevel(),
                eventObject.getThreadName(),
                eventObject.getLoggerName(),
                message
        );
        System.out.print(line);
    }
}
