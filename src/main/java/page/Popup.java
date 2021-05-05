package page;

import driver.DriverManager;
import org.openqa.selenium.Alert;

public class Popup {
    Alert alert = DriverManager.getDriver().switchTo().alert();

    public HomePage clickOKAlert(){

        alert.accept();
        return new HomePage();
    }

    public Popup clickOKAlertWithWarning(){
        alert.accept();
        return this;
    }

    public String getPopupMessage(){
        return alert.getText();
    }

    public boolean verifyAlertMessage(String mess){
        return getPopupMessage().equals(mess);
    }

    public boolean verifyWarningMessage(String pageName){
        return String.format("Cannot delete page '%s' since it has child page(s).\" appears",pageName).equals(DriverManager.getDriver().switchTo().alert().getText());

    }



}
