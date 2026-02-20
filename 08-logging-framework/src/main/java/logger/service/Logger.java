package logger.service;

import logger.appender.LogAppender;
import logger.models.LogLevel;
import logger.models.LogMessage;

public class Logger {
    private static volatile Logger instance;
    private volatile LoggerConfig config;

    private Logger() {
        this.config = new LoggerConfig(LogLevel.DEBUG, new logger.appender.ConsoleAppender());
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setConfig(LoggerConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config must be non-null");
        }
        this.config = config;
    }

    public synchronized void log(LogLevel level, String message) {
        if (!level.isAtLeast(config.getMinLevel())) {
            return;
        }
        LogMessage logMessage = new LogMessage(level, message);
        config.getAppender().append(logMessage);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}
