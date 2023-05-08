package app.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Logging {
    private static final Logger logger = LogManager.getLogger(Logging.class);

    public abstract void log(String msg);

    public static Logger getLogger() {
        return logger;
    }
}