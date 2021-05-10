package page;

import common.Constant;
import common.Logger;
import driver.DriverManager;
import element.BaseElement;
import element.Element;
import enums.TimeOut;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    /**
     * Open the TA Dashboard
     *
     * @return Login Page
     * @author tuan.vu
     */


    protected static final common.Logger Logger = new Logger(BaseElement.class.getName());
    private final Element lblUsername = new Element("//a[@href='#Welcome']");

    private final Element elmAcountInfor(String itemName) {
        return new Element(String.format("//a[text()='%s']", itemName));
    }

    public LoginPage logout() {
        lblUsername.waitForDisplayed();
        try {
            lblUsername.moveMouse();
            elmAcountInfor("Logout").click();
        } catch (Exception e) {
            lblUsername.moveMouse();
            elmAcountInfor("Logout").click();
        }
        return new LoginPage();
    }


    public LoginPage open() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
        return new LoginPage();
    }

    public String getPopupText() {
        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout()).until(ExpectedConditions.alertIsPresent());
        Alert alert = DriverManager.getDriver().switchTo().alert();
        return alert.getText();
    }

    public String getTitle(){
        return DriverManager.getDriver().getTitle();
    }


}
