package page;

import driver.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupAlert {
    private Alert alert;
    private void getAlert(){

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 2 /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            alert = DriverManager.getDriver().switchTo().alert();
        } catch (Exception e) {
            wait.until(ExpectedConditions.alertIsPresent());
            alert = DriverManager.getDriver().switchTo().alert();
        }
    }

    public HomePage clickOKAlert(){
        getAlert();
        alert.accept();
        return new HomePage();
    }

    public HomePage closeAlert(){
        getAlert();
        alert.dismiss();
        return new HomePage();
    }

    public String getPopupMessage(){
        getAlert();
        return alert.getText();
    }

    public boolean verifyAlertMessage(String mess){
        String actual = getPopupMessage().replaceAll("\n","");
        return actual.equals(mess);
    }




}
