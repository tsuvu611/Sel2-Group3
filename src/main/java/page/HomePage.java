package page;

import driver.DriverManager;
import element.Button;
import element.Element;
import element.Label;
import enums.TimeOut;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
    private final Label lblUsername = new Label("//a[@href='#Welcome']");
    private final Label lblTabName = new Label("//a[@class='active']");
    private final Element elmGlobalSetting = new Element("//li[@class='mn-setting']");

    private final Label elmBesideRight(String pageName) {
        return new Label(String.format("//a[text()='%s']/../following-sibling::li[1]/a", pageName));
    }

    private final Label elmPageName(String pageName) {
        return new Label(String.format("//a[contains(text(),'%s')]", pageName));
    }

    private final Label elmItemSetting(String itemName) {
        return new Label(String.format("//a[text()='%s']", itemName));
    }

    private final Label elmChildPageName(String parentPageName, String childPageName) {
        String tmp = String.format("//a[contains(text(),'%s')]/following-sibling::ul", parentPageName);
        return new Label(String.format(tmp + "//a[contains(text(),'%s')]", childPageName));
    }
    private final Button btnLogout = new Button("//a[@href='logout.do']");

    public String getTabName() {
        return this.lblTabName.getText();
    }

    public HomePage moveToGlobalSetting() {
        elmGlobalSetting.waitForDisplayed();
        elmGlobalSetting.moveMouse();
        return this;
    }

    public HomePage clickPage(String pageName) {
        elmPageName(pageName).waitForDisplayed();
        elmPageName(pageName).click();
        return this;
    }

    public PagePopup clickAddNewPage() {
        Logger.info("Clicking Add Page Item");
        try {
            elmItemSetting("Add Page").click();
        } catch (Exception e) {
            moveToGlobalSetting();
            elmItemSetting("Add Page").click();
        }
        return new PagePopup();
    }

    public PopupAlert clickDeletePage() {
        Logger.info("Clicking Detele Page Item");
        try {
            elmItemSetting("Delete").click();
        } catch (Exception e) {
            moveToGlobalSetting();
            elmItemSetting("Delete").click();
        }
        return new PopupAlert();
    }

    public PagePopup clickEditPage() {
        Logger.info("Clicking Edit Page Item");
        try {
            elmItemSetting("Edit").click();
        } catch (Exception e) {
            moveToGlobalSetting();
            elmItemSetting("Edit").click();
        }
        return new PagePopup();
    }

    public boolean isAddNewPageDisplayed() {
        return elmItemSetting("Add Page").isDisplayed();
    }

    public boolean isNewPageDisplayedBesideOverview(String pageName) {
        elmPageName(pageName).waitForDisplayed();
        return pageName.equals(elmBesideRight("Overview").getText());
    }

    public boolean isPagePositioned(String pageName1, String pageName2) {
        elmPageName(pageName2).waitForDisplayed();
        return elmBesideRight(pageName1).getText().equals(pageName2);
    }

    public boolean isPageNavigated(String pageName){
        return this.getTitle().contains(pageName);
    }

    public boolean isPageFollowOverview(String pageName){
        try {
            if (!elmBesideRight("Overview").isDisplayed()) {
                elmBesideRight("Overview").waitForDisplayed();
            }
            return elmBesideRight("Overview").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage clickPageBesideOverview() {
        elmBesideRight("Overview").click();
        return this;
    }

    public HomePage moveMouseToPage(String pageName) {
        try {
            elmPageName(pageName).moveMouse();
        }
        catch (Exception e){
            elmPageName(pageName).moveMouse();
        }
        return this;
    }

    public HomePage moveMouseToChildPage(String parentPageName, String childPageName) {
        try {
            elmPageName(childPageName).moveMouse();
        } catch (Exception e) {
            elmPageName(parentPageName).moveMouse();
            elmChildPageName(parentPageName, childPageName).moveMouse();
        }
        return this;
    }

    public boolean isChildPageDisplayed(String parentPageName, String childPageName) {
        try {
            if (!elmChildPageName(parentPageName, childPageName).isDisplayed()) {
                elmPageName(parentPageName).moveMouse();
            }
            return elmChildPageName(parentPageName, childPageName).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageDisplayed(String pageName) {
        try {
            elmPageName(pageName).waitForDisplayed();
            return elmPageName(pageName).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDeleteButtonDisplay() {
        try {
            return elmItemSetting("Delete").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public HomePage clickChildPage(String parentPageName, String childPageName) {
        try {
            elmChildPageName(parentPageName, childPageName).click();
        } catch (Exception e) {
            elmPageName(parentPageName).moveMouse();
            elmChildPageName(parentPageName, childPageName).click();
        }
        return this;
    }


    public LoginPage logout() {
        lblUsername.click();
        btnLogout.click();
        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout());
        return new LoginPage();
    }
}
