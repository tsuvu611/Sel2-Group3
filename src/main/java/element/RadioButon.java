package element;

import enums.RadioButtonLegends;
import enums.RadioButtonStyle;
import enums.RadioButtonType;
import org.openqa.selenium.By;


public class RadioButon extends BaseElement {

    public RadioButon(By locator) {
        super(locator);
    }

    public RadioButon(String xpath) {
        super(xpath);
    }

//    public void clickRadioButtonType(RadioButtonType radioButtonType){
//        new RadioButon(radioButtonType.getRadioID()).click();
//    }
//
//    public void clickRadioButtonStyle(RadioButtonStyle radioButtonStyle){
//        new RadioButon(radioButtonStyle.getRadioID()).click();
//    }
//
//    public void clickRadioButtonStyle(RadioButtonLegends radioButtonLegends){
//        new RadioButon(radioButtonLegends.getRadioID()).click();
//    }

}
