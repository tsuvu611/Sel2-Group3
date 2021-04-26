package element;

import org.openqa.selenium.By;


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
