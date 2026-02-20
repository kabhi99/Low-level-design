package logger;

import logger.appender.ConsoleAppender;
import logger.appender.DatabaseAppender;
import logger.models.LogLevel;
import logger.service.Logger;
import logger.service.LoggerConfig;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getInstance();

        logger.debug("Debug message");
        logger.info("Info message");
        logger.warning("Warning message");
        logger.error("Error message");
        logger.fatal("Fatal message");

        System.out.println("\n--- Filtering by min level (INFO) ---");
        logger.setConfig(new LoggerConfig(LogLevel.INFO, new ConsoleAppender()));
        logger.debug("Hidden debug");
        logger.info("Visible info");
        logger.warning("Visible warning");

        System.out.println("\n--- Database appender ---");
        logger.setConfig(new LoggerConfig(LogLevel.ERROR, new DatabaseAppender()));
        logger.info("Hidden info");
        logger.error("Visible error to DB");

        System.out.println("\n--- File appender ---");
        Path logFile = Path.of(System.getProperty("java.io.tmpdir"), "logger-demo.log");
        logger.setConfig(new LoggerConfig(LogLevel.DEBUG, new logger.appender.FileAppender(logFile)));
        logger.info("Written to file: " + logFile);

        System.out.println("\n--- Multi-threaded logging ---");
        logger.setConfig(new LoggerConfig(LogLevel.DEBUG, new ConsoleAppender()));
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 8; i++) {
            final int id = i;
            executor.submit(() -> logger.info("Thread " + id + " logged"));
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
