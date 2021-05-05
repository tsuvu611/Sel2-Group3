package element;

import driver.DriverManager;
import org.openqa.selenium.By;


public class Label extends BaseElement {
    /**
     * @param locator the By locator
     * @author tuan.vu
     * Instantiates a new element
     */
    public Label(By locator) {
        super(locator);
    }

    /**
     * @param xpath the By.xpath
     * @author tuan.vu
     * Instantiates a new element
     */
    public Label(String xpath) {
        super(xpath);
    }

    public String getText(){
        return getElement().getText();
    }
}
