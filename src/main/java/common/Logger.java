package common;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
public class Logger {
    public static java.util.logging.Logger logger;

    public Logger(String className){
        createLogger(className);
    }

    public static void createLogger(String className) {
        logger = java.util.logging.Logger.getLogger(className);
    }

    public void info(String message){
        logger.info(message);
    }
    public void error(String message) {logger.severe(message);}

    public void warning(String message){
        logger.warning(message);
    }
}
