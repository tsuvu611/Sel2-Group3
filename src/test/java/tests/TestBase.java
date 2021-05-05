package tests;

import common.Logger;
import driver.DriverManager;
import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;

public class TestBase {
    protected static final Logger Logger = new Logger(TestBase.class.getName());
    protected WebDriver driver() {
        return DriverManager.getDriver();
    }


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run beforeMethod");
        DriverManager.createDriver(DriverType.CHROME);
        LoginPage loginPage = new LoginPage();
        loginPage.open();
    }

//    @AfterMethod
    public void afterMethod() {
        System.out.println("Run after Method");
        driver().quit();
    }

    public void assertTrue(boolean condition, String msg) {

        Assert.assertTrue(condition,msg);
    }
    public void assertFalse(boolean condition, String msg) {

        Assert.assertFalse(condition,msg);
    }
}
