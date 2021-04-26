package tests;

import common.Logger;
import driver.DriverManager;
import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.HomePage;

public class TestBase {
    protected static final Logger Logger = new Logger(TestBase.class.getName());
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run beforeMethod");
        DriverManager.createDriver(DriverType.CHROME);
        HomePage homePage = new HomePage();
        homePage.open();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Run after Method");
        getDriver().quit();
    }
}
