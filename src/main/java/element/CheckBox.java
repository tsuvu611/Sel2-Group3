package element;

import org.openqa.selenium.By;

public class CheckBox extends BaseElement{

    /**
     * @author khoi.nguyen
     *  Instantiates a new CheckBox
     * @param locator the By locator
     */
    public CheckBox(By locator) {
        super(locator);
    }

    /**
     * @author khoi.nguyen
     * Instantiates a new checkBox
     * @param xpath the By.xpath
     */
    public CheckBox(String xpath) {
        super(xpath);
    }

    public void checkTheCheckBox(){
        if(!getElement().isSelected()) getElement().click();
    }
    public void uncheckTheCheckBox(){
        if(getElement().isSelected()) getElement().click();
    }
}
