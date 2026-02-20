package logger.service;

import logger.appender.LogAppender;
import logger.models.LogLevel;

public class LoggerConfig {
    private final LogLevel minLevel;
    private final LogAppender appender;

    public LoggerConfig(LogLevel minLevel, LogAppender appender) {
        if (minLevel == null || appender == null) {
            throw new IllegalArgumentException("minLevel and appender must be non-null");
        }
        this.minLevel = minLevel;
        this.appender = appender;
    }

    public LogLevel getMinLevel() {
        return minLevel;
    }

    public LogAppender getAppender() {
        return appender;
    }
}
