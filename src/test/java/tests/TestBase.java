package tests;

import common.Logger;
import driver.DriverManager;
import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.HomePage;
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

    @AfterMethod
    public void afterMethod() {
        System.out.println("Run after Method");
        driver().quit();
    }
}
