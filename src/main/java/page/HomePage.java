package page;

import element.Element;
import element.Label;

public class HomePage extends BasePage {
    private final Label lblTabName = new Label("//a[@class='active']");
    private final Element elmGlobalSetting = new Element("//li[@class='mn-setting']");

    private final Element elmBesideRight(String pageName) {
        return new Element(String.format("//a[text()='%s']/../following-sibling::li[1]/a", pageName));
    }

    private final Element elmPageName(String pageName) {
        return new Element(String.format("//a[contains(text(),'%s')]", pageName));
    }

    private final Element elmItemSetting(String itemName) {
        return new Element(String.format("//a[text()='%s']", itemName));
    }

    private final Element elmChildPageName(String parentPageName, String childPageName) {
        String tmp = String.format("//a[contains(text(),'%s')]/following-sibling::ul", parentPageName);
        return new Element(String.format(tmp + "//a[contains(text(),'%s')]", childPageName));
    }

    private final Element elmChild2PageName(String parentPageName, String childPageName1,String childPageName2) {
        String tmp = String.format("//a[contains(text(),'%s')]/following-sibling::ul", parentPageName);
        String tmp2 = String.format(tmp+"//a[contains(text(),'%s')]", childPageName1);

        return new Element(String.format(tmp2 + "//a[contains(text(),'%s')]", childPageName2));
    }

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

    public boolean verifyAddNewPageIsDisplayed() {
        return elmItemSetting("Add Page").isDisplayed();
    }

    public boolean verifyNewPageDisplaysBesideOverview(String pageName) {
        elmPageName(pageName).waitForDisplayed();
        return pageName.equals(elmBesideRight("Overview").getText());
    }

    public boolean verifyPageIsPositioned(String pageName1, String pageName2) {
        elmPageName(pageName2).waitForDisplayed();
        return elmBesideRight(pageName1).getText().equals(pageName2);
    }

    public HomePage clickPageBesideOverview() {
        elmBesideRight("Overview").click();
        return this;
    }

    public HomePage moveMouseToPage(String pageName) {
        elmPageName(pageName).moveMouse();
        return this;
    }

    public HomePage moveMouseToChildPage(String pageName) {
        elmPageName(pageName).moveMouse();
        return this;
    }

    public boolean verifyChildPageIsDisplayed(String parentPageName, String childPageName) {
        if (!elmChildPageName(parentPageName, childPageName).isDisplayed()) {
            elmPageName(parentPageName).moveMouse();
        }
        return elmChildPageName(parentPageName, childPageName).isDisplayed();
    }

    public boolean verifyPageIsVisible(String pageName) {
        try {
            elmPageName(pageName).waitForDisplayed();
            return elmPageName(pageName).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean verifyDeleteDisplay() {
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


}
