package element;

import common.Logger;
import driver.DriverManager;
import enums.TimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BaseElement {
    protected static final Logger Logger = new Logger(BaseElement.class.getName());
    protected WebElement _element = null;
    protected List<WebElement> _elements = null;

    private By _byLocator;

    /**
     * @param locator the By locator
     * @author tuan.vu
     * Instantiates a new base element
     */
    public BaseElement(By locator) {
        this._byLocator = locator;
    }

    /**
     * @param xpath the By.xpath
     * @author tuan.vu
     * Instantiates a new base element
     */
    public BaseElement(String xpath) {
        this._byLocator = By.xpath(xpath);
    }

    protected void waitForDisplayed(int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(_byLocator));
        } catch (TimeoutException e) {
            Logger.warning(String.format("Element does not display after %n", timeout));
        }
    }

    public void waitForDisplayed(TimeOut timeout) {
        waitForDisplayed(timeout.getTimeout());
    }

    public void waitForDisplayed() {
        waitForDisplayed(TimeOut.TIMEOUT);
    }
}
