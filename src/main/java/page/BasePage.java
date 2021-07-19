package page;

import common.Constant;
import common.Logger;
import driver.DriverManager;
import element.*;
import enums.TimeOut;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    /**
     * Open the TA Dashboard
     *
     * @return Login Page
     * @author tuan.vu
     */


    protected static final common.Logger Logger = new Logger(BaseElement.class.getName());
    protected final Element lblUsername = new Element("//a[@href='#Welcome']");

    protected final Element elmAcountInfor(String itemName) {
        return new Element(String.format("//a[text()='%s']", itemName));
    }

    protected final Label lblTabName = new Label("//a[@class='active']");
    protected final Label lblGlobalSetting = new Label("//li[@class='mn-setting']");
    protected final Label lblPanelSetting = new Label("//li[@class='mn-panels']");
    protected final Label lblAdminister = new Label("//a[@href='#Administer']");
    protected static final element.Alert alert = new element.Alert();

    protected final Label elmBesideRight(String pageName) {
        return new Label(String.format("//a[text()='%s']/../following-sibling::li[1]/a", pageName));
    }

    protected final Label elmAfter(String pageName1,String pageName2) {
        return new Label(String.format("%s//following::%s", pageName1,pageName2.substring(2)));
    }

    protected final Label elmPageName(String pageName) {
        return new Label(String.format("//a[contains(text(),'%s')]", pageName));
    }

    protected final Label elmChildItem(String itemName) {
        return new Label(String.format("//a[text()='%s']", itemName));
    }

    protected final Label elmChildPageName(String parentPageName, String childPageName) {
        String tmp = String.format("//a[contains(text(),'%s')]/following-sibling::ul", parentPageName);
        return new Label(String.format(tmp + "//a[contains(text(),'%s')]", childPageName));
    }

    protected final Button btnLogout = new Button("//a[@href='logout.do']");

    public String getTabName() {
        return this.lblTabName.getText();
    }

    public LoginPage logout() {
        lblUsername.waitForDisplayed();
        try {
            lblUsername.moveMouse();
            elmAcountInfor("Logout").click();
        } catch (Exception e) {
            lblUsername.moveMouse();
            elmAcountInfor("Logout").click();
        }
        return new LoginPage();
    }


    public LoginPage open() {
        DriverManager.getDriver().navigate().to(Constant.DASHBOARD_URL);
        DriverManager.getDriver().manage().window().maximize();
        return new LoginPage();
    }

    public String getAlertText() {
        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout()).until(ExpectedConditions.alertIsPresent());
        Alert alert = DriverManager.getDriver().switchTo().alert();
        return alert.getText();
    }

    public String getTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public void clickOKAlert(){
        Logger.info("Clicking OK ALert");
        alert.clickOKAlert();
    }

    public void clickCancelAlert(){
        Logger.info("Clicking Cancel ALert");
        alert.closeAlert();
    }

    public void closeAlert(){
        Logger.info("Closing ALert");
        alert.clickOKAlert();
    }

    public boolean isAlertMessageCorrect(String mess) {
        return alert.isAlertMessageCorrect(mess);
    }


    public HomePage moveMouseToGlobalSetting() {
        lblGlobalSetting.waitForDisplayed();
        lblGlobalSetting.moveMouse();
        return new HomePage();
    }

    public HomePage clickAdminister() {
        lblAdminister.waitForDisplayed();
        lblAdminister.click();
        return new HomePage();
    }

    public PagePopup clickAddNewPage() {
        Logger.info("Clicking Add Page Item");
        try {
            elmChildItem("Add Page").waitsForPresent(TimeOut.TIMEOUT);
            elmChildItem("Add Page").click();
        } catch (Exception e) {
            moveMouseToGlobalSetting();
            elmChildItem("Add Page").click();
        }
        return new PagePopup();
    }

    public AddNewPanelPopup clickCreatePanel() {
        Logger.info("Clicking Create Panel Item");
        try {
            elmChildItem("Create Panel").click();
        } catch (Exception e) {
            moveMouseToGlobalSetting();
            elmChildItem("Create Panel").click();
        }
        return new AddNewPanelPopup();
    }

    public PanelPage clickPanels() {
        Logger.info("Clicking Create Panel Item");
        try {
            elmChildItem("Panels").click();
        } catch (Exception e) {
            clickAdminister();
            elmChildItem("Panels").click();
        }
        return new PanelPage();
    }

    public HomePage clickPage(String pageName) {
        elmPageName(pageName).waitForPresent(TimeOut.TIMEOUT);
        elmPageName(pageName).click();
        return new HomePage();
    }

    public HomePage moveMouseToAdminister() {
        lblAdminister.waitForDisplayed();
        lblAdminister.moveMouse();
        return new HomePage();
    }









}
