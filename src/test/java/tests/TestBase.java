package tests;

import common.Logger;
import driver.DriverManager;
import enums.DriverType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;

public class TestBase {
    protected static final Logger Logger = new Logger(TestBase.class.getName());

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run beforeMethod");
        DriverManager.createDriver(DriverType.CHROME);
        LoginPage loginPage = new LoginPage();
        Logger.info("Step 1. Navigate to Dashboard login page");
        loginPage.open();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Run after Method");
        DriverManager.getDriver().quit();
    }

    public void assertTrue(boolean condition, String msg) {
        Assert.assertTrue(condition, msg);
    }

    public void assertFalse(boolean condition, String msg) {
        Assert.assertFalse(condition, msg);
    }
}
