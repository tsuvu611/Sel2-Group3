package tests;

import common.Constant;
import common.Utility;
import data.Repository;
import data.User;

import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

import static org.testng.Assert.*;

public class LoginTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Test
    public void DA_LOGIN_TC001() {
        Logger.info("Verify that user can login specific repository successfully via Dashboard login page with correct credentials");
        Repository repository = new Repository();
        User user = new User();
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button
        homePage = loginPage.login(repository, user);

        // 4. Verify that Dashboard Main page appears
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC002() {
        Logger.info("Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials");
        Repository repository = new Repository();
        User user = new User();
        String expectedMsg = "Username or password is invalid";
        String actualMsg;

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password
        // 3. Click on "Login" button
        loginPage = loginPage.loginWithInvalidAccount(repository, user.getRandomUser());

        // 4. Verify that Dashboard Error message "Username or password is invalid" appears
        actualMsg = loginPage.getPopupText();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC003() {
        Logger.info("Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password");
        Repository repository = new Repository();
        User user = new User(Utility.randomString(3),"");
        String expectedMsg = "Username or password is invalid";
        String actualMsg;

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and invalid password
        // 3. Click on "Login" button
        loginPage = loginPage.loginWithInvalidAccount(repository, user);

        // 4. Verify that Dashboard Error message "Username or password is invalid" appears
        actualMsg = loginPage.getPopupText();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC004() {
        Logger.info("Verify that user is able to log in different repositories successfully after logging out current repository");
        Repository repository = new Repository();
        User user = new User();
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        // 1. Navigate to Dashboard login page
        // 2. Enter valid username and password of default repository
        // 3. Click on "Login" button
        homePage = loginPage.login(repository, user);

        // 4. Click on "Logout" button
        loginPage = homePage.logout();

        // 5. Select a different repository
        // 6. Enter valid username and password of this repository
        Repository new_repository = new Repository(Constant.NEW_REPOSITORY);
        homePage = loginPage.login(new_repository, user);

        // 7. Verify that Dashboard Mainpage appears
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }


}
