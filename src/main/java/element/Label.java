package element;

import org.openqa.selenium.By;


public class Label extends BaseElement{
    /**
     * @author tuan.vu
     *  Instantiates a new element
     * @param locator the By locator
     */
    public Label(By locator) {
        super(locator);
    }

    /**
     * @author tuan.vu
     * Instantiates a new element
     * @param xpath the By.xpath
     */
    public Label(String xpath) {
        super(xpath);
    }
}
