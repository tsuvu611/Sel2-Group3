package tests;

import common.Constant;
import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class LoginTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Test
    public void DA_LOGIN_TC001() {
        User user = new User();
        String expectedMsg = "Execution Dashboard";

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button

        homePage = loginPage.login(Constant.REPOSITORY, user);

        // 4. Verify that Dashboard Mainpage appears

        String actualMsg = homePage.getTabName();
        Assert.assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC002(){
        User user = new User();
        String expectedMsg = "Username or password is invalid";

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button

        loginPage = loginPage.loginWithInvalidAccount(Constant.REPOSITORY, user.getRandomUser());

        // 4. Verify that Dashboard Error message "Username or password is invalid" appears

        String actualMsg = loginPage.getPopupText();
        Assert.assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

}
