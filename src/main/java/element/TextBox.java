package element;

import org.openqa.selenium.By;


public class TextBox extends BaseElement {
    /**
     * @param locator the By locator
     * @author tuan.vu
     * Init new TextBox
     */
    public TextBox(By locator) {
        super(locator);
    }

    /**
     * @param xpath the By.xpath
     * @author tuan.vu
     * Init new TextBox
     */
    public TextBox(String xpath) {
        super(xpath);
    }

    public void enter(String text) {
        Logger.info(String.format("Entering on text box located at %s",getLocator()));
        getElement().sendKeys(text);
    }

    public void clear(){
        Logger.info(String.format("Clearing on text box located at %s",getLocator()));
        getElement().clear();
    }

}
