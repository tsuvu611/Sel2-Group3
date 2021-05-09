package page;

import driver.DriverManager;
import element.Button;
import element.Label;
import enums.TimeOut;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final Label lblUsername = new Label("//a[@href='#Welcome']");
    private final Label lblTabName = new Label("//a[@class='active']");
    private final Button btnLogout = new Button("//a[@href='logout.do']");

    public String getTabName() {
        return this.lblTabName.getText();
    }

    public LoginPage logout() {
        lblUsername.click();
        btnLogout.click();
        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout());
        return new LoginPage();
    }
}
