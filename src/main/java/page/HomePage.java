package page;

import driver.DriverManager;
import element.Button;
import element.Element;
import element.Label;
import enums.TimeOut;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final Label lblUsername = new Label("//div[@id-'test']");
    private final Button btnLogout = new Button("//a[@href='logout.do']");
    private final Element eleUsername = new Element("//a[@href='#Welcome']");
    private final Label lblTabName = new Label("//a[@class='active']");

    public String getTabName(){
        return this.lblTabName.getText();
    }

    public LoginPage logout(){
        eleUsername.click();
        btnLogout.click();
        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout());
        return new LoginPage();
    }
}
