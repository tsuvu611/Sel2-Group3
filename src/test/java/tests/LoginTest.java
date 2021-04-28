package tests;

import common.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class LoginTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Test
    public void Test1() {
        Logger.info("Try to open chrome driver");

        String expectedMsg = "Execution Dashboard";
        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button
        homePage = loginPage.login(Constant.REPOSITORY, Constant.USERNAME, Constant.PASSWORD);
        // 4. Verify that Dashboard Mainpage appears
        String actualMsg = homePage.getTabName();
        Assert.assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void Test2(){
        Logger.info("Try to open chrome driver");
        String expectedMsg = "Username or password is invalid";
        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button
        homePage = loginPage.login(Constant.REPOSITORY,"abc", "abc");
        // 4. Verify that Dashboard Error message "Username or password is invalid" appears
        String actualMsg = loginPage.getPopupText();
        Assert.assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

}
