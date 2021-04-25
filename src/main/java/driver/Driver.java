package driver;


import common.Logger;
import enums.DriverType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Driver extends BaseDriver {
    private static final common.Logger Logger = new Logger(Driver.class.getName());
    private static DesiredCapabilities caps;
    private WebDriver _driver;

    /**
     * @param type     the type
     * @param parallel the parallel
     * @param hub      the hub
     * @author tuan.vu
     * Instantiates a new driver
     */
    public Driver(DriverType type, boolean parallel, String hub) {
        try {
            Logger.info(String.format("Create new %s Driver", type.getValue()));
            switch (type.getValue()) {
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir") + "\\src\\main\\java\\driver\\chromedriver.exe");
                    if (!parallel) {
                        _driver = new ChromeDriver();
                    } else if (parallel) {
                        caps = DesiredCapabilities.chrome();
                        _driver = new RemoteWebDriver(new URL(hub), caps);
                    }
                    DriverManager.addDriver(_driver);
                    break;

                case "Firefox":
                    System.setProperty("webdriver.gecko.driver",
                            System.getProperty("user.dir") + "\\Core\\core.drivers\\geckodriver.exe");
                    if (!parallel) {
                        _driver = new FirefoxDriver();
                    } else if (parallel) {
                        caps = DesiredCapabilities.firefox();
                        _driver = new RemoteWebDriver(new URL(hub), caps);
                    }

                    DriverManager.addDriver(_driver);

                    break;
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private static JavascriptExecutor jsExecutor() {
        return ((JavascriptExecutor) getDriver());
    }

    public static Object executeScript(String script, Object... args) {
        return jsExecutor().executeScript(script);
    }


    public static void scrollTillEnd() {
        Logger.info(String.format("Scroll the driver %s till end", DriverManager.getThreadId()));
        String js = String.format("window.scrollTo(0, document.body.scrollHeight)");
        jsExecutor().executeScript(js);
    }
}
