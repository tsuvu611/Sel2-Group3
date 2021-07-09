package element;

import com.google.common.base.Stopwatch;
import enums.TimeOut;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ComboBox extends BaseElement {
    /**
     * @param locator the By locator
     * @author tuan.vu
     * Init new Combobox
     */
    public ComboBox(By locator) {
        super(locator);
    }

    /**
     * @param xpath the By.xpath
     * @author tuan.vu
     * Init new Combobox
     */
    public ComboBox(String xpath) {
        super(xpath);
    }

    protected Select selection(int timeOutInSeconds) {
        waitForDisplayed();
        return new Select(_element);
    }

    /**
     * @param timeOutInSeconds the time out in seconds
     * @param text             the text
     * @author tuan.vu
     * Select by text
     */
    private void selectByText(int timeOutInSeconds, String text) {
        if (timeOutInSeconds <= 0) {
            Logger.error("The time out is invalid. It must greater than 0");
            return;
        }
        Stopwatch sw = Stopwatch.createStarted();
        try {
            Logger.info(String.format("Select the option of the control %s by text", getXpath().toString()));
            selection(timeOutInSeconds).selectByVisibleText(text);
        } catch (StaleElementReferenceException ex) {
            if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
                Logger.warning(String.format("Try to select the option of the control %s by text again",
                        getXpath()));
                selectByText(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), text);
            }
        } catch (Exception error) {
            Logger.error(String.format("Has error with control '%s': %s", getXpath(), error.getMessage()));
            throw error;
        }
    }

    public void selectByText(String text) {
        selectByText(TimeOut.SHORT_TIMEOUT.getTimeout(), text);
    }

    private void selectByIndex(int timeOutInSeconds, int index) {
        if (timeOutInSeconds <= 0) {
            Logger.error("The time out is invalid. It must greater than 0");
            return;
        }
        Stopwatch sw = Stopwatch.createStarted();
        try {
            Logger.info(String.format("Select the option of the control %s by text", getXpath().toString()));
            selection(timeOutInSeconds).selectByIndex(index);
        } catch (StaleElementReferenceException ex) {
            if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
                Logger.warning(String.format("Try to select the option of the control %s by text again",
                        getXpath()));
                selectByIndex(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), index);
            }
        } catch (Exception error) {
            Logger.error(String.format("Has error with control '%s': %s", getXpath(), error.getMessage()));
            throw error;
        }
    }

    public void selectByIndex(int index) {
        selectByIndex(TimeOut.SHORT_TIMEOUT.getTimeout(), index);
    }

    public ArrayList<String> getListData(){
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(this.getText().split("\n")));
        return myList;
    }

    public boolean isCmbAlphabetOrder() {
        this.waitForDisplayed();
        ArrayList<String> tmp = new ArrayList<>();
        tmp = getListData();
        ArrayList<String> tmp2 = new ArrayList<>(tmp);
        Collections.sort(tmp2, String.CASE_INSENSITIVE_ORDER);
        return tmp.equals(tmp2);
    }

    public String getSelectedText() {
        waitForDisplayed();
        return selection((TimeOut.SHORT_TIMEOUT.getTimeout())).getFirstSelectedOption().getText();
    }



}
