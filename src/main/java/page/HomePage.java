package page;

import element.Element;
import element.Label;

public class HomePage extends BasePage {
    private final Label lblUsername = new Label("//div[@id-'test']");
    private final Label lblRepoName = new Label("xpath will be updated later");
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

    public String getTabName() {
        return this.lblTabName.getText();
    }

    public HomePage moveToGlobalSetting() {
        elmGlobalSetting.waitForDisplayed();
        elmGlobalSetting.click();
        return this;
    }

    public HomePage clickPage(String pageName){
        elmPageName(pageName).waitForDisplayed();
        elmPageName(pageName).click();
        return this;
    }

    public PagePopup clickAddNewPage() {
        Logger.info("Clicking Add Page Item");
        elmItemSetting("Add Page").click();
        return new PagePopup();
    }

    public Popup clickDeletePage() {
        Logger.info("Clicking Detele Page Item");
        elmItemSetting("Delete").click();
        return new Popup();
    }

    public PagePopup clickEditPage() {
        Logger.info("Clicking Edit Page Item");
        elmItemSetting("Edit").click();
        return new PagePopup();
    }

    public boolean verifyAddNewPageIsDisplayed() {
        return elmGlobalSetting.isDisplayed();
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
        elmPageName(pageName).waitForDisplayed();
        elmPageName(pageName).moveMouse();
        return this;
    }

    public boolean verifyChildPageIsDisplayed(String parentPageName, String childPageName) {
       try {
           return elmChildPageName(parentPageName,childPageName).isDisplayed();
       }
       catch (Exception e){
           return false;
       }
    }

    public boolean verifyPageIsVisible(String pageName){
        try {
            return elmPageName(pageName).isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }

    public HomePage clickChildPage(String parentPageName,String childPageName){
        elmChildPageName(parentPageName, childPageName).click();
        return this;
    }

    public boolean verifyChildPageAddSucess(String parentPage,String childPageName){
        moveMouseToPage(parentPage);
        return elmChildPageName(parentPage,childPageName).isDisplayed();
    }




}
