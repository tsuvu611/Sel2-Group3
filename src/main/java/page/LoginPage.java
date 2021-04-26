package page;

import element.Button;
import element.ComboBox;
import element.TextBox;

public class LoginPage extends GeneralPage {
    // Controls
    private final ComboBox cbbRepository = new ComboBox("//select[@id='repository']");
    private final TextBox txtUserName = new TextBox("//input[@id='username']");
    private final TextBox txtPassword = new TextBox("//input[@id='password']");
    private final Button btnLogin = new Button("//div[@class='btn-login']");

    public HomePage login(String repository, String username, String password) {
        cbbRepository.selectByText(repository);
        txtUserName.enter(username);
        txtPassword.enter(password);
        btnLogin.click();
        return new HomePage();
    }


}
