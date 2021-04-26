package element;

import driver.Driver;
import org.openqa.selenium.By;


public class TextBox extends BaseElement{
    /**
     * @author tuan.vu
     *
     *         Instantiates a new element.
     *
     * @param locator the By locator
     */
    public TextBox(By locator) {
        super(locator);
    }

    /**
     * @author tuan.vu
     *
     *         Instantiates a new element.
     *
     * @param xpath the By.xpath
     */
    public TextBox(String xpath) {
        super(xpath);
    }

    public void enter(String text) {
        _element.clear();
        _element.sendKeys(text);
    }

}
