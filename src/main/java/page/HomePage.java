package page;

import common.Constant;
import driver.DriverManager;
import element.Label;

public class HomePage {
    private final Label lblUsername = new Label("xpath will be updated later");
    private final Label lblRepoName = new Label("xpath will be updated later");

    /**
     * Open the TA Dashboard
     * @author tuan.vu
     * @return the home page
     */
    public HomePage open() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
        return this;
    }

    public HomePage waitForLoading() {
        lblUsername.waitForDisplayed();
        return this;
    }

}
