package page;

import common.Constant;
import driver.DriverManager;
import org.openqa.selenium.Alert;

public abstract class BasePage {
    /**
     * Open the TA Dashboard
     *
     * @return Login Page
     * @author tuan.vu
     */
    public LoginPage open() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
        return new LoginPage();
    }

    public String getPopupText(){
        Alert alert = DriverManager.getDriver().switchTo().alert();
        return   alert.getText();
    }
}
