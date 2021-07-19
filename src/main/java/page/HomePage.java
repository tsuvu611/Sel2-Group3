package page;

import driver.Driver;
import driver.DriverManager;
import element.Button;
import element.ListElement;
import enums.TimeOut;

import javax.swing.text.EditorKit;

public class HomePage extends BasePage {
    private final Button btnEdit = new Button("(//*[contains(@title,'Edit Panel')])[1]");

    public HomePage clickDeletePage() {
        Logger.info("Clicking Detele Page Item");
        try {
            elmChildItem("Delete").click();
        } catch (Exception e) {
            moveMouseToGlobalSetting();
            elmChildItem("Delete").click();
        }
        return this;
    }

    public AddNewPanelPopup clickEdit(){
        btnEdit.waitForDisplayed();
        btnEdit.click();
        return new AddNewPanelPopup();
    }

    public PagePopup clickEditPage() {
        Logger.info("Clicking Edit Page Item");
        try {
            elmChildItem("Edit").click();
        } catch (Exception e) {
            moveMouseToGlobalSetting();
            elmChildItem("Edit").click();
        }
        return new PagePopup();
    }

    public boolean isAddNewPageDisplayed() {
        return elmChildItem("Add Page").isDisplayed();
    }

    public boolean isNewPageDisplayedBesideOverview(String pageName) {
        elmPageName(pageName).waitForDisplayed();
        return pageName.equals(elmBesideRight("Overview").getText());
    }

    public boolean isPagePositioned(String pageName1, String pageName2) {
        elmPageName(pageName2).waitForDisplayed();
        return elmBesideRight(pageName1).getText().equals(pageName2);
    }

    public boolean isPageNavigated(String pageName) {
        return this.getTitle().contains(pageName);
    }

    public boolean isPageFollowOverview(String pageName) {
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
        } catch (Exception e) {
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
            elmChildItem("Delete").waitForDisplayed();
            return elmChildItem("Delete").isDisplayed();
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


//    public LoginPage logout() {
//        lblUsername.click();
//        btnLogout.click();
//        new WebDriverWait(DriverManager.getDriver(), TimeOut.TIMEOUT.getTimeout());
//        return new LoginPage();
//    }

    public ChoosePanelPopup clickPanelSetting() {
        lblPanelSetting.waitForDisplayed();
        lblPanelSetting.click();
        return new ChoosePanelPopup();
    }



}
