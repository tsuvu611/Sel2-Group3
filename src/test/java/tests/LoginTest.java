package tests;

import org.testng.annotations.Test;
import page.LoginPage;

public class LoginTest extends TestBase {
    private final LoginPage loginPage = new LoginPage();

    @Test
    public void Test1() {
        Logger.info("Try to open chrome driver");
        loginPage.login("SampleRepository", "administrator", "");
    }


}
