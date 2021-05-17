package tests;

import common.DataHelper;
import data.Repository;
import data.User;
import driver.DriverManager;
import enums.DriverType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.PopupAlert;

import static org.testng.Assert.assertEquals;

public class DataProfilePageTest extends TestBase{
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private PopupAlert popupAlert = new PopupAlert();
    private User user;


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run beforeMethod");
        Repository repository = new Repository();
        User user = new User();
        homePage = loginPage.login(repository, user);
    }

    @Test
    public void DA_DP_TC065() {
        Logger.info("DA_DP_TC065 - Verify that all Pre-set Data Profiles are populated correctly");
        Repository repository = new Repository();
        User user = new User();
        String expectedMsg = "Execution Dashboard";
        String actualMsg;

        Logger.info("1. Navigate to Dashboard login page");
        Logger.info("2. Select a specific repository ");
        Logger.info("3. Enter valid Username and Password");
        homePage = loginPage.login(repository, user);

        Logger.info("4. Click Login");
        Logger.info("5. Click Administer->Data Profiles");
        Logger.info("6. Check Pre-set Data Profile are populated correctly in profiles page");
        actualMsg = homePage.getTabName();
        assertEquals(actualMsg, expectedMsg, "Title is not displayed as expected");
    }
}
