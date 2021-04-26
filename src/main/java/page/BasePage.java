package page;

import common.Constant;
import driver.DriverManager;

public abstract class BasePage {
    public void open123() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
    }
}
