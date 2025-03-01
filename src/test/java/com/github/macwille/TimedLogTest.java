package com.github.macwille;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public final class TimedLogTest {
    private final String examplePath = "src/test/resources/example";

    @BeforeAll
    public static void createTestDirectory() {
        Assertions.assertDoesNotThrow(() -> Files.createDirectories(Paths.get("src/test/resources")));
    }

    @AfterEach
    public void cleanUp() {
        Assertions.assertDoesNotThrow(() -> {
            Files.deleteIfExists(Paths.get(examplePath));
        });
    }

    @Test
    public void testFileCreated() {
        final Log log = new TimedLog(examplePath);
        Assertions.assertDoesNotThrow(log::write);
        Assertions.assertTrue(Files.exists(Paths.get(examplePath)));
        Assertions.assertDoesNotThrow(() -> {
            String content = new String(Files.readAllBytes(Paths.get(examplePath)));
            Assertions.assertTrue(content.contains("Example service called"));
        });
    }

    @Test
    public void testCorrectDateTime() {
        final LocalDateTime dateTime = LocalDateTime.of(2010, 3, 10, 12, 30, 45);
        final Log log = new TimedLog(examplePath, dateTime);
        Assertions.assertDoesNotThrow(log::write);
        Assertions.assertTrue(Files.exists(Paths.get(examplePath)));
        String expectedLog = "[2010-03-10T12:30:45] Example service called";
        Assertions.assertDoesNotThrow(() -> {
            String content = new String(Files.readAllBytes(Paths.get(examplePath)));
            Assertions.assertTrue(content.endsWith(System.lineSeparator()));
            Assertions.assertEquals(expectedLog, content.trim());
        });
    }
}
