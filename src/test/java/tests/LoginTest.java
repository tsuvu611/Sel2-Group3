package tests;

import common.DataHelper;
import data.Repository;
import data.User;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.PopupAlert;

import static org.testng.Assert.assertEquals;

public class LoginTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Test
    public void DA_LOGIN_TC001() {
        Logger.info("DA_LOGIN_TC001 - Verify that user can login specific repository successfully via Dashboard login page with correct credentials");
        Repository repository = new Repository();
        User user = new User();
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Enter valid username and password");
        Logger.info("3. Click on \"Login\" button");
        homePage = loginPage.login(repository, user);

        Logger.info("4. Verify that Dashboard Main page appears");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC002() {
        Logger.info("DA_LOGIN_TC002 - Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials");
        Repository repository = new Repository();
        User user = new User("abc", "abc");
        String expectedMsg = "Username or password is invalid";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Enter valid username and password");
        Logger.info("3. Click on \"Login\" button");
        loginPage = loginPage.loginInvalid(repository, user);

        Logger.info("4. Verify that Dashboard Error message \"Username or password is invalid\" appears");
        actualMsg = loginPage.getPopupMessage();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC003() {
        Logger.info("DA_LOGIN_TC003 - Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password");
        Repository repository = new Repository();
        User user = new User();
        user.setPassword("abc");
        String expectedMsg = "Username or password is invalid";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Enter valid username and invalid password");
        Logger.info("3. Click on \"Login\" button");
        loginPage = loginPage.loginInvalid(repository, user);

        Logger.info("4. Verify that Dashboard Error message \"Username or password is invalid\" appears");
        actualMsg = loginPage.getPopupMessage();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC004() {
        Logger.info("DA_LOGIN_TC004 - Verify that user is able to log in different repositories successfully after logging out current repository");
        Repository repository = new Repository();
        Repository newRepository = DataHelper.getTestRepo();
        User user = new User();
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Enter valid username and password of default repository");
        Logger.info("3. Click on \"Login\" button");
        homePage = loginPage.login(repository, user);

        Logger.info("// 4. Click on \"Logout\" button");
        loginPage = homePage.logout();

        Logger.info("5. Select a different repository");
        Logger.info("6. Enter valid username and password of this repository");
        homePage = loginPage.login(newRepository, user);

        Logger.info("7. Verify that Dashboard Mainpage appears");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC005() {
        Logger.info("DA_LOGIN_TC005 - Verify that there is no Login dialog when switching between 2 repositories with the same account");
        Repository repository = new Repository();
        Repository newRepository = DataHelper.getTestRepo();
        User user = new User();
        String newRepoName = "TestRepository";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Login with valid account for the first repository");
        homePage = loginPage.login(repository, user);

        Logger.info("3. Choose another repository in Repository list");
        homePage = homePage.changeRepo(newRepoName);

        Logger.info("4. Observe the current page");
        actualMsg = homePage.getRepoName(newRepoName);
        assertEquals(actualMsg, newRepoName, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC006() {
        Logger.info("DA_LOGIN_TC006 - Verify that \"Password\" input is case sensitive");
        Repository repository = new Repository();
        User user = new User("test","TEST");
        User invalidUser = new User("test","test");
        String expectedMsg_1 = "Execution Dashboard";
        String expectedMsg_2 = "Username or password is invalid";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Login with the account has uppercase password");
        homePage = loginPage.login(repository, user);

        Logger.info("3. Main page is displayed");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg_1, "Title is not displayed as expected");

        Logger.info("4. Logout TA Dashboard");
        loginPage = homePage.logout();

        Logger.info("5. Login with the above account but enter lowercase password");
        loginPage = loginPage.loginInvalid(repository, invalidUser);

        Logger.info("6. Dashboard Error message \"Username or password is invalid\" appears");
        actualMsg = loginPage.getPopupMessage();
        assertEquals(actualMsg, expectedMsg_2, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC007() {
        Logger.info("DA_LOGIN_TC007 - Verify that \"Username\" is not case sensitive");
        Repository repository = new Repository();
        User user = new User("UPPERCASEUSERNAME","uppercaseusername");
        User otherUser = new User("uppercaseusername","uppercaseusername");
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Login with the account has uppercase username");
        homePage = loginPage.login(repository, user);

        Logger.info("3. Main page is displayed");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");

        Logger.info("4. Logout TA Dashboard");
        loginPage = homePage.logout();

        Logger.info("5. Login with the above account but enter lowercase username");
        homePage = loginPage.login(repository, otherUser);

        Logger.info("6. Dashboard Error message \"Username or password is invalid\" appears");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC008() {
        Logger.info("DA_LOGIN_TC008 - Verify that password with special characters is working correctly");
        Repository repository = new Repository();
        User user = new User("specialCharsPassword","`!@^&*(+_=[{;'\",./<?");
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Login with account that has special characters password");
        homePage = loginPage.login(repository, user);

        Logger.info("3. Main page is displayed");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC009() {
        Logger.info("DA_LOGIN_TC009 - Verify that username with special characters is working correctly");
        Repository repository = new Repository();
        User user = new User("`~!@$^&()',.","specialCharsUser");
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Login with account that has special characters username ");
        homePage = loginPage.login(repository, user);

        Logger.info("3. Main page is displayed");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }

    @Test
    public void DA_LOGIN_TC010() {
        Logger.info("DA_LOGIN_TC010 - Verify that the page works correctly for the case when no input entered to Password and Username field");
        Repository repository = new Repository();
        User user = new User("","");
        String expectedMsg = "Please enter username";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Click Login button without entering data into Username and Password field");
        loginPage = loginPage.loginInvalid(repository, user);

        Logger.info("3. Message \"Please enter username\" is displayed");
        actualMsg = loginPage.getPopupMessage();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }
}
