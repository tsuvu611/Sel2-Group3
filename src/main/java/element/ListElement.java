package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListElement extends BaseElement {
    public ListElement(By locator) {
        super(locator);
    }

    public ListElement(String xpath) {
        super(xpath);
    }

    public List<String> getElementsText(){
        List<String> result = new ArrayList<>();
        for (WebElement we:getElements()){
            result.add(we.getText());
        }
        return result;
    }
}
