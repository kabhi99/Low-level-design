package logger.appender;

import logger.models.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
}
