package logger.appender;

import logger.models.LogMessage;

public class DatabaseAppender implements LogAppender {
    @Override
    public void append(LogMessage message) {
        System.out.println("[DB] " + message);
    }
}
