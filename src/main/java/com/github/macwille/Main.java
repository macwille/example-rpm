package com.github.macwille;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        if (args.length != 1) {
            LOGGER.error("Error with system arguments, only 1 argument is supported but length was <{}>", args.length);
            System.exit(1);
        }
        final String filePath = args[0];
        final LocalDateTime dateTime = LocalDateTime.now();
        final Log log = new TimedLog(filePath, dateTime);

        try {
            log.write();
            LOGGER.info("Finished writing message for date time <{}>", dateTime);
            System.exit(0); // execution successful
        } catch (final RuntimeException e) {
            LOGGER.error("Error writing log with exception: <{}>", e.getMessage(), e);
            System.exit(1); // execution failed
        }
    }
}
