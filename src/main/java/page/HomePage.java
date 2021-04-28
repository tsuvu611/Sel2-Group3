package page;

import common.Constant;
import driver.DriverManager;
import element.Element;
import element.Label;
import org.openqa.selenium.By;

public class HomePage {
    private final Label lblUsername = new Label("//div[@id-'test']");
    private final Label lblRepoName = new Label("xpath will be updated later");
    private final Label lblTabName = new Label("//a[@class='active']");

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

    public String getTabName(){
        return this.lblTabName.getText();
    }


}
