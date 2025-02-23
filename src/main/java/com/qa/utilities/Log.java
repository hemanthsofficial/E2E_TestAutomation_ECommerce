package com.qa.utilities;

import org.slf4j.LoggerFactory;
import java.util.logging.Logger;

public class Log {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warning(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }
}