package element;

import driver.Driver;
import page.HomePage;

public class Alert {
    public void clickOKAlert(){
        Driver.getAlert().accept();
    }

    public void closeAlert(){
        Driver.getAlert().dismiss();
    }

    public String getPopupMessage(){
        return Driver.getAlert().getText();
    }

    public boolean isAlertMessageCorrect(String mess){
        String actual = getPopupMessage().replaceAll("\n","");
        return actual.equals(mess);
    }
}
