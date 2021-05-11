package driver;

import common.Logger;
import enums.DriverType;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DriverManager {
    private static final Logger Logger = new Logger(DriverManager.class.getName());
    private static Map<String, WebDriver> map = new ConcurrentHashMap<>() {
    };

    /**
     * @author tuan.vu
     * Creates the driver
     * @param type the type
     */
    public static void createDriver(DriverType type) {
        new Driver(type, false, null);
    }

    /**
     * @author tuan.vu
     * Creates the driver
     * @param type the type
     * @param hub  the hub
     */
    public static void createDriver(DriverType type, String hub) {
        new Driver(type, true, hub);
    }

    /**
     * @author tuan.vu
     * Gets the thread id
     * @return the thread id
     */
    protected static String getThreadId() {
        return String.valueOf(Thread.currentThread().getId());
    }

    /**
     * @author tuan.vu
     * Adds the driver into the thread
     * @param driver the driver
     */
    protected static void addDriver(WebDriver driver) {
        try {
            Logger.info("Adding driver on thread " + getThreadId());
            map.put(getThreadId(), driver);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
    public static WebDriver getDriver() {
        return map.get(getThreadId());
    }
}
