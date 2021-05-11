package element;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;


public class Element extends BaseElement{
    /**
     * @author tuan.vu
     *  Instantiates a new element
     * @param locator the By locator
     */
    public Element(By locator) {
        super(locator);
    }

    /**
     * @author tuan.vu
     * Instantiates a new element
     * @param xpath the By.xpath
     */
    public Element(String xpath) {
        super(xpath);
    }



}
