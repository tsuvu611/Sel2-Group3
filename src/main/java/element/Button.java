package element;

import org.openqa.selenium.By;


public class Button extends BaseElement{
    /**
     * @author tuan.vu
     *  Instantiates a new Button
     * @param locator the By locator
     */
    public Button(By locator) {
        super(locator);
    }

    /**
     * @author tuan.vu
     * Instantiates a new Button
     * @param xpath the By.xpath
     */
    public Button(String xpath) {
        super(xpath);
    }
}
