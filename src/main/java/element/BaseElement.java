package element;

import common.Logger;
import driver.Driver;
import driver.DriverManager;
import enums.TimeOut;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class BaseElement {
    protected static final Logger Logger = new Logger(BaseElement.class.getName());
    protected WebElement _element = null;
    protected List<WebElement> _elements = null;

    private final By _byLocator;
    private String _xpath = null;

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
        this._xpath = xpath;
    }

    /**
     * @return the By locator of the control
     */
    protected By getLocator() {
        return this._byLocator;
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    /**
     * @param timeOutInSeconds the time out in seconds
     * @return the Web Element
     * @author tuan.vu
     * Wait for the Web Element to be present in DOM with a specific timeout
     */
    protected WebElement waitForPresent(int timeOutInSeconds) {
        Logger.info(String.format("Wait for control %s to be present in DOM with timeOut %s", getXpath().toString(),
                timeOutInSeconds));
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
            _element = wait.until(ExpectedConditions.presenceOfElementLocated(getLocator()));
        } catch (Exception error) {
            Logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
            throw error;
        }
        return _element;
    }

    public WebElement waitForPresent(TimeOut timeout) {
        return waitForPresent(timeout.getTimeout());
    }

    protected void waitForDisplayed(int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
            _element = wait.until(ExpectedConditions.visibilityOfElementLocated(_byLocator));
        } catch (TimeoutException e) {
            Logger.warning(String.format("Element does not display after %n", timeout));
            throw e;
        }
    }

    public void waitForDisplayed(TimeOut timeout) {
        waitForDisplayed(timeout.getTimeout());
    }

    protected WebElement getElement() {
        return waitForPresent(TimeOut.TIMEOUT);
    }

    public void waitForDisplayed() {
        waitForDisplayed(TimeOut.TIMEOUT);
    }

    /**
     * @param timeOutInSeconds the time out in seconds
     * @return the Web Element
     * @author khoi.nguyen
     * Wait for the List<WebElement> to be present in DOM with a specific timeout
     */
    protected List<WebElement> waitsForPresent(int timeOutInSeconds) {
        Logger.info(String.format("Wait for control %s to be present in DOM with timeOut %s", getXpath().toString(),
                timeOutInSeconds));
        try {
            WebDriverWait wait2 = new WebDriverWait(getDriver(), timeOutInSeconds);
            _elements = wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator()));
        } catch (Exception error) {
            Logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
            throw error;
        }
        return _elements;
    }

    public List<WebElement> waitsForPresent(TimeOut timeout) {
        return waitsForPresent(timeout.getTimeout());
    }

    protected void waitsForDisplayed(int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
            _elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_byLocator));
        } catch (TimeoutException e) {
            Logger.warning(String.format("Element does not display after %n", timeout));
            throw e;
        }
    }

    public void waitsForDisplayed(TimeOut timeout) {
        waitsForDisplayed(timeout.getTimeout());
    }

    protected List<WebElement> getElements() {
        return waitsForPresent(TimeOut.TIMEOUT);
    }

    public void waitsForDisplayed() {
        waitsForDisplayed(TimeOut.TIMEOUT);
    }

    //-------------------------------------------------------------------------------------
    public String getText(){
        return  this.waitForPresent(TimeOut.TIMEOUT).getText();
    }

    public boolean isDisplayed(){
        return this.waitForPresent(TimeOut.TIMEOUT) .isDisplayed();
    }

    public String getXpath() {
        return _xpath;
    }

    public void click() {
        Logger.info(String.format("Clicking on element located at %s",getLocator()));
        try {
            this.waitForDisplayed();
            getElement().click();
        }
        catch (StaleElementReferenceException e){
            this.waitForDisplayed();
            getElement().click();
        }
    }

    public void moveMouse(){
        Logger.info(String.format("Moving on element located at %s",getLocator()));
        this.waitForDisplayed();
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(getElement()).perform();
    }

    public boolean isEnable(){
        return this.waitForPresent(TimeOut.TIMEOUT).isEnabled();
    }

    public boolean isSelected(){
        return getElement().isSelected();
    }



}
