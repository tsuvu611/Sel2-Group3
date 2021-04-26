package page;

import common.Constant;
import driver.DriverManager;
import element.Label;

public class HomePage {
    private final Label lblUsername = new Label("//div[@id-'test']");
    private final Label lblRepoName = new Label("xpath will be updated later");

    /**
     * Open the TA Dashboard
     *
     * @return the home page
     * @author tuan.vu
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
