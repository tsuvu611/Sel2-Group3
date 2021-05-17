package page;

import data.Repository;
import data.User;
import driver.DriverManager;
import element.Button;
import element.ComboBox;
import element.TextBox;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends GeneralPage {
    // Controls
    private final ComboBox cbbRepository = new ComboBox("//select[@id='repository']");
    private final TextBox txtUserName = new TextBox("//input[@id='username']");
    private final TextBox txtPassword = new TextBox("//input[@id='password']");
    private final Button btnLogin = new Button("//div[@class='btn-login']");
    private Alert alert;

    public HomePage login(Repository repository, User user) {
        cbbRepository.selectByText(repository.getRepoName());
        txtUserName.enter(user.getUsername());
        txtPassword.enter(user.getPassword());
        btnLogin.click();
        return new HomePage();
    }

    public LoginPage loginInvalid(Repository repository, User user){
        cbbRepository.selectByText(repository.getRepoName());
        txtUserName.enter(user.getUsername());
        txtPassword.enter(user.getPassword());
        btnLogin.click();
        return new LoginPage();
    }

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

    public String getPopupMessage(){
        getAlert();
        return alert.getText();
    }
}
