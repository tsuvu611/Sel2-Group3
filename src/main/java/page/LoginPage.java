package page;

import data.User;
import element.Button;
import element.ComboBox;
import element.TextBox;

public class LoginPage extends GeneralPage {
    // Controls
    private final ComboBox cbbRepository = new ComboBox("//select[@id='repository']");
    private final TextBox txtUserName = new TextBox("//input[@id='username']");
    private final TextBox txtPassword = new TextBox("//input[@id='password']");
    private final Button btnLogin = new Button("//div[@class='btn-login']");

    public HomePage login(String repository, User user) {
        cbbRepository.selectByText(repository);
        txtUserName.enter(user.getUsername());
        txtPassword.enter(user.getPassword());
        btnLogin.click();
        return new HomePage();
    }

    public LoginPage loginWithInvalidAccount(String repository, User user){
        cbbRepository.selectByText(repository);
        txtUserName.enter(user.getUsername());
        txtPassword.enter(user.getPassword());
        btnLogin.click();
        return new LoginPage();
    }
}
