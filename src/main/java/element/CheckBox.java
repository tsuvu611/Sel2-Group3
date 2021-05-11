package element;

import enums.CheckBoxState;
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

    public void setCheckBoxState(CheckBoxState checkBoxState){
        if (checkBoxState.getState().equals("on")){
            if(!getElement().isSelected()) getElement().click();
        }
        else if (checkBoxState.getState().equals("off")){
            if(getElement().isSelected()) getElement().click();
        }
    }
}
