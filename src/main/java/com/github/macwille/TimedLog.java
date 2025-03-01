package com.github.macwille;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public final class TimedLog implements Log {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimedLog.class);

    private final String filePath;
    private final LocalDateTime dateTime;

    public TimedLog(final String filePath) {
        this(filePath, LocalDateTime.now());
    }

    public TimedLog(final String filePath, final LocalDateTime dateTime) {
        this.filePath = filePath;
        this.dateTime = dateTime;
    }

    public void write() {
        try(final FileWriter writer = new FileWriter(this.filePath, true)) {
            final String logMessage = logMessage();
            LOGGER.info("Writing log message <{}> for date <{}>", logMessage, dateTime);
            writer.write(logMessage);
        } catch (final IOException e) {
            throw new RuntimeException("Error appending file: " + e.getMessage(), e);
        }
    }

    private String logMessage() {
        return String.format("[%s] Example service called%s", dateTime, System.lineSeparator());
    }
}
