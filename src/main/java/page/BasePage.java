package page;

import common.Constant;
import driver.DriverManager;

public abstract class BasePage {
    /**
     * Open the TA Dashboard
     *
     * @return the home page
     * @author tuan.vu
     */
    public HomePage open() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
        return new HomePage();
    }
}
