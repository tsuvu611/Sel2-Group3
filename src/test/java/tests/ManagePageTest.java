package tests;

import common.Constant;
import common.Utility;
import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.PagePopup;
import page.PopupAlert;

public class ManagePageTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private PagePopup pagePopup = new PagePopup();
    private PopupAlert popupAlert = new PopupAlert();

    //    @Test(description = "Verify that user is unable open more than 1 \"New Page\" dialog")
    public void DA_MP_TC011() {
        User user = new User();
        Logger.info("DA_MP_TC011 - Verify that user is unable open more than 1 \"New Page\" dialog");
        Logger.info("Step 2. Login with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Try to go to Global Setting -> Add page again");
        homePage.moveToGlobalSetting();

        Logger.info("VP: Observe the current page");
        Assert.assertFalse(homePage.verifyAddNewPageIsDisplayed(), "The Add new page button is displayed as not expected.");

        Logger.info("Post-Condition");
        Logger.info("Log-out");
        homePage.logout();

    }

    //    @Test(description = "Verify that user is able to add additional pages besides \"Overview\" page successfully")
    public void DA_MP_TC012() {
        User user = new User();
        String newPageName = Utility.randomString(5);
        boolean isRightPageName;
        Logger.info("DA_MP_TC012 - Verify that user is able to add additional pages besides \"Overview\" page successfully");

        Logger.info("Step 2. Login with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageName);

        Logger.info("Step 5. Click OK button");
        pagePopup.clickOK();

        Logger.info("VP: Check \"Test\" page is displayed besides \"Overview\" page");
        isRightPageName = homePage.verifyNewPageDisplaysBesideOverview(newPageName);
        assertTrue(isRightPageName, "The New Page Name is not displayed as expected.");

        Logger.info("Post-Condition");
        Logger.info("Delete new page");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that the newly added main parent page is positioned at the location specified as set with \"Displayed After\" field of \"New Page\" form on the main page bar/\"Parent Page\" dropped down menu")
    public void DA_MP_TC013() {
        User user = new User();
        String newPageName1 = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        boolean isRightPosition;
        Logger.info("Verify that the newly added main parent page is positioned at the location specified as set with \"Displayed After\" field of \"New Page\" form on the main page bar/\"Parent Page\" dropped down menu");

        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageName1);

        Logger.info("Step 5. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 6. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 7. Enter page name fill");
        pagePopup.enterPageName(newPageName2);

        Logger.info("Step 8. Click on Displayed After dropdown list");
        pagePopup.clickDisplayAfter();

        Logger.info("Step 9. Select specific page");
        pagePopup.selectDisplayAfter(newPageName1);

        Logger.info("Click OK button");
        pagePopup.clickOK();

        Logger.info("VP:Check \"Another Test\" page is positioned besides the \"Test\" page");
        isRightPosition = homePage.verifyPageIsPositioned(newPageName1, newPageName2);
        assertTrue(isRightPosition, "The " + newPageName2 + " page is not positioned besides the " + newPageName1 + " page as expected.");

        Logger.info("Post-Condition");
        Logger.info("Delete newly added main child page and its parent page");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
        homePage.clickPage(newPageName1);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that \"Public\" pages can be visible and accessed by all users of working repository")
    public void DA_MP_TC014() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName = Utility.randomString(5);
        boolean isNewPageDisplay;
        Logger.info("DA_MP_TC014 - Verify that the newly added main parent page is positioned at the location specified as set with \"Displayed After\" field of \"New Page\" form on the main page bar/\"Parent Page\" dropped down menu");

        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageName);

        Logger.info("Step 5. Check Public checkbox");
        pagePopup.checkThePubblicCheckBox();

        Logger.info("Step 6. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 7. Click on Log out link");
        homePage.logout();

        Logger.info("Step 8. Log in with another valid account");
        loginPage.login(Constant.REPOSITORY, user2);

        Logger.info("VP: Check newly added page is visibled");
        isNewPageDisplay = homePage.verifyNewPageDisplaysBesideOverview(newPageName);
        assertTrue(isNewPageDisplay, "The New Page Name is not displayed in another valid account.");

        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete new page");
        homePage.logout();
        loginPage.login(Constant.REPOSITORY, user);
        homePage.clickPage(newPageName);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that non \"Public\" pages can only be accessed and visible to their creators with condition that all parent pages above it are \"Public\"")
    public void DA_MP_TC015() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName1 = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        boolean isChildPageDisplay;

        Logger.info("DA_MP_TC015 - Verify that non \"Public\" pages can only be accessed and visible to their creators with condition that all parent pages above it are \"Public\"");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageName1);

        Logger.info("Step 5. Check Public checkbox");
        pagePopup.checkThePubblicCheckBox();

        Logger.info("Step 6. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 7. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 8. Enter page name fill");
        pagePopup.enterPageName(newPageName2);

        Logger.info("Step 9. Click on  Select Parent dropdown list");
        pagePopup.clickCmbParentPage();

        Logger.info("Step 9. Select specific page");
        pagePopup.selectParentPage(newPageName1);

        Logger.info("Step 10. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 11. Click on Log out link");
        homePage.logout();

        Logger.info("Step 12. Log in with another valid account");
        loginPage.login(Constant.REPOSITORY, user2);

        Logger.info("VP: Check children is invisibled");
        homePage.moveMouseToPage(newPageName1);
        isChildPageDisplay = homePage.verifyChildPageIsDisplayed(newPageName1, newPageName2);
        assertFalse(isChildPageDisplay, "The Child Page is displayed as not expected in another valid account.");

        Logger.info("Post-Condition");
        Logger.info("Log in  as creator page account and delete newly added page and its parent page");
        homePage.logout();
        loginPage.login(Constant.REPOSITORY, user);
        homePage.moveMouseToPage(newPageName1);
        homePage.clickChildPage(newPageName1, newPageName2);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
        homePage.clickPage(newPageName1);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that user is able to edit the \"Public\" setting of any page successfully")
    public void DA_MP_TC016() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName1 = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        boolean isPopupEditDisplayed, isPageVisibile;

        Logger.info("DA_MP_TC016 - Verify that user is able to edit the \"Public\" setting of any page successfully");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageName1);

        Logger.info("Step 5. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 6. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 7. Enter page name fill");
        pagePopup.enterPageName(newPageName2);

        Logger.info("Step 8. Check Public checkbox");
        pagePopup.checkThePubblicCheckBox();

        Logger.info("Step 9. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 10. Click on " + newPageName1 + " page");
        homePage.clickPage(newPageName1);

        Logger.info("Step 11. Click on \"Edit\" link");
        homePage.moveToGlobalSetting();
        homePage.clickEditPage();

        Logger.info("VP: Check \"Edit Page\" pop up window is displayed");
        isPopupEditDisplayed = pagePopup.verifyEditPopupIsDisplayed();
        assertTrue(isPopupEditDisplayed, "Edit Popup is not displayed as expected.");

        Logger.info("Step 13. Check Public checkbox");
        pagePopup.checkThePubblicCheckBox();

        Logger.info("Step 14. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 15. Click on " + newPageName2 + " page");
        homePage.clickPage(newPageName2);

        Logger.info("Step 16. Click on \"Edit\" link");
        homePage.moveToGlobalSetting();
        homePage.clickEditPage();

        Logger.info("VP: Check \"Edit Page\" pop up window is displayed");
        isPopupEditDisplayed = pagePopup.verifyEditPopupIsDisplayed();
        assertTrue(isPopupEditDisplayed, "Edit Popup is not displayed as expected.");

        Logger.info("Step 18. Uncheck Public checkbox");
        pagePopup.uncheckThePubblicCheckBox();

        Logger.info("Step 19. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 20. Click on Log out link");
        homePage.logout();

        Logger.info("Step 21. Log in with another valid account");
        loginPage.login(Constant.REPOSITORY, user2);

        Logger.info("VP: Check " + newPageName1 + " Page is visible and can be accessed");
        isPageVisibile = homePage.verifyPageIsVisible(newPageName1);
        assertTrue(isPageVisibile, "Page is not visible as expected.");

        Logger.info("VP: Check " + newPageName2 + " Page is visible and can be accessed");
        isPageVisibile = homePage.verifyPageIsVisible(newPageName2);
        assertFalse(isPageVisibile, "Page is visible as not expected.");

        Logger.info("Post-Condition");
        Logger.info("Log in  as creator page account and delete newly added page");
        homePage.logout();
        loginPage.login(Constant.REPOSITORY, user);
        homePage.clickPage(newPageName2);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
        homePage.clickPage(newPageName1);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that user can remove any main parent page except \"Overview\" page successfully and the order of pages stays persistent as long as there is not children ")
    public void DA_MP_TC017() {
        User user = new User();
        String newPageNameParent = Utility.randomString(5);
        String newPageNameChild = Utility.randomString(5);
        String confirmDeleteMessage = "Are you sure you want to remove this page?";
        String wariningDeleteMessage = "Cannot delete page " + newPageNameParent + " since it has children page(s)";
        boolean isExactALertMassage, isPageDelete, isDeleteDisplay;

        Logger.info("DA_MP_TC17 - Verify that user can remove any main parent page except \"Overview\" page successfully and the order of pages stays persistent as long as there is not children ");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Add a new parent page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(newPageNameParent);
        pagePopup.clickOK();

        Logger.info("Step 4. Add a children page of newly added page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(newPageNameChild);
        pagePopup.clickCmbParentPage();
        pagePopup.selectParentPage(newPageNameParent);
        pagePopup.clickOK();

        Logger.info("Step 5. Click on parent page");
        homePage.clickPage(newPageNameParent);

        Logger.info("Step 6. Click \"Delete\" link");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();

        Logger.info("VP: Check confirm message \"Are you sure you want to remove this page?\" appears");
        isExactALertMassage = popupAlert.verifyAlertMessage(confirmDeleteMessage);
        assertTrue(isExactALertMassage, "Alert message is not displayed as expected.");

        Logger.info("Step 8. Click OK button");
        popupAlert.clickOKAlert();

        Logger.info("VP: Check warning message \"Can not delete page " + newPageNameParent + " since it has children page(s)\" appears");
        isExactALertMassage = popupAlert.verifyAlertMessage(wariningDeleteMessage);
        assertTrue(isExactALertMassage, "Alert warning message is not displayed as expected");

        Logger.info("Step 10. Click OK button");
        popupAlert.clickOKAlert();

        Logger.info("Step 11. Click on  children page");
        homePage.moveMouseToPage(newPageNameParent);
        homePage.clickChildPage(newPageNameParent, newPageNameChild);

        Logger.info("Step 12. Click \"Delete\" link");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();

        Logger.info("VP: Check confirm message \"Are you sure you want to remove this page?\" appears");
        isExactALertMassage = popupAlert.verifyAlertMessage(confirmDeleteMessage);
        assertTrue(isExactALertMassage, "Alert message is not displayed as expected.");

        Logger.info("Step 14. Click OK button");
        popupAlert.clickOKAlert();

        Logger.info("VP: Check children page is deleted");
        homePage.clickPage(newPageNameParent);
        isPageDelete = homePage.verifyChildPageIsDisplayed(newPageNameParent, newPageNameChild);
        assertFalse(isPageDelete, "Children page is not deleted as expected.");

        Logger.info("Step 16. Click on parent page");
        homePage.clickPage(newPageNameParent);

        Logger.info("Step 17. Click \"Delete\" link");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();

        Logger.info("VP: Check confirm message \"Are you sure you want to remove this page?\" appears");
        isExactALertMassage = popupAlert.verifyAlertMessage(confirmDeleteMessage);
        assertTrue(isExactALertMassage, "Alert message is not displayed as expected.");

        Logger.info("Step 19. Click OK button");
        popupAlert.clickOKAlert();

        Logger.info("VP: Check Parent page is deleted");
        isPageDelete = homePage.verifyPageIsVisible(newPageNameParent);
        assertFalse(isPageDelete, "Parent page is not deleted as expected.");

        Logger.info("Step 21. Click on \"Overview\" page");
        homePage.clickPage("Overview");

        Logger.info("VP: Check \"Delete\" link disappears");
        homePage.moveToGlobalSetting();
        isDeleteDisplay = homePage.verifyDeleteDisplay();
        assertFalse(isDeleteDisplay, "Delete link is displayed as not expected");

        Logger.info("Post-Condition");
        Logger.info("Close TA Dashboard Main Page");

    }

    //    @Test(description = "Verify that user is able to add additional sibbling pages to the parent page successfully")
    public void DA_MP_TC018() {
        User user = new User();
        String newPageNameParent = Utility.randomString(5);
        String newPageNameChild1 = Utility.randomString(5);
        String newPageNameChild2 = Utility.randomString(5);
        Boolean isPageChildIsAdded;

        Logger.info("DA_MP_TC18 - Verify that user is able to add additional sibbling pages to the parent page successfully");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageNameParent);

        Logger.info("Step 5. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 6. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 7. Enter page name fill");
        pagePopup.enterPageName(newPageNameChild1);

        Logger.info("Step 8. Click on  Parent Page dropdown list");
        pagePopup.clickCmbParentPage();

        Logger.info("Step 9. Select a parent page");
        pagePopup.selectParentPage(newPageNameParent);

        Logger.info("Step 10. Click OK button");
        pagePopup.clickOK();

        Logger.info("Step 11. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 12. Enter page name fill");
        pagePopup.enterPageName(newPageNameChild2);

        Logger.info("Step 13. Click on  Parent Page dropdown list");
        pagePopup.clickCmbParentPage();

        Logger.info("Step 14. Select a parent page");
        pagePopup.selectParentPage(newPageNameParent);

        Logger.info("Step 15. Click OK button");
        pagePopup.clickOK();

        Logger.info("VP: Check " + newPageNameChild2 + " is added successfully");
        homePage.moveMouseToPage(newPageNameParent);
        isPageChildIsAdded = homePage.verifyChildPageIsDisplayed(newPageNameParent, newPageNameChild2);
        assertTrue(isPageChildIsAdded, newPageNameChild2 + " is not added as expected");

        Logger.info("Post-Condition");
        Logger.info("Log in  as creator page account and delete newly added page, its sibling page and parent page");
        homePage.moveMouseToPage(newPageNameParent);
        homePage.clickChildPage(newPageNameParent, newPageNameChild2);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
        homePage.moveMouseToPage(newPageNameParent);
        homePage.clickChildPage(newPageNameParent, newPageNameChild1);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
        homePage.clickPage(newPageNameParent);
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();
        popupAlert.clickOKAlert();
    }

    //    @Test(description = "Verify that user is able to add additional sibbling page levels to the parent page successfully.")
    public void DA_MP_TC019() {
        User user = new User();
        String newPageNameParent = "Overview";
        String newPageNameChild = Utility.randomString(5);
        Boolean isPageChildIsAdded;

        Logger.info("DA_MP_TC19 - Verify that user is able to add additional sibbling page levels to the parent page successfully.");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageNameChild);
        pagePopup.clickCmbParentPage();
        pagePopup.selectParentPage(newPageNameParent);
        pagePopup.clickOK();

        Logger.info("VP: User is able to add additional sibbling page levels to parent page successfully");
        homePage.moveMouseToPage(newPageNameParent);
        isPageChildIsAdded = homePage.verifyChildPageIsDisplayed(newPageNameParent, newPageNameChild);
        assertTrue(isPageChildIsAdded, newPageNameChild + " is not added as expected");

        Logger.info("Post-Condition");

    }

//    @Test(description = "Verify that user is able to delete sibbling page as long as that page has not children page under it")
    //Need to fix
    public void DA_MP_TC020() {
        User user = new User();
        String newPageNameParent = "Overview";
        String newPageNameChild1 = Utility.randomString(5);
        String newPageNameChild2 = Utility.randomString(5);
        String wariningDeleteMessage = "Cannot delete page " + newPageNameChild1 + " since it has children page(s)";
        Boolean isExactALertMassage, isPageDisplay;

        Logger.info("DA_MP_TC20 - Verify that user is able to delete sibbling page as long as that page has not children page under it");
        Logger.info("Step 2. Log in specific repository with valid account");
        loginPage.login(Constant.REPOSITORY, user);

        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name fill");
        pagePopup.enterPageName(newPageNameChild1);
        pagePopup.clickCmbParentPage();
        pagePopup.selectParentPage(newPageNameParent);
        pagePopup.clickOK();

        Logger.info("Step 5. Go to Global Setting -> Add page");
        homePage.moveToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 6. Enter page name fill");
        pagePopup.enterPageName(newPageNameChild2);
        pagePopup.clickCmbParentPage();
        pagePopup.selectParentPage("    "+newPageNameChild1);
        pagePopup.clickOK();

        Logger.info("Step 7. Go to the first created page");
        homePage.moveMouseToPage(newPageNameParent);
        homePage.clickChildPage(newPageNameParent, newPageNameChild1);

        Logger.info("Step 8. Click \"Delete\" link");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();

        Logger.info("Step 9. Click Ok button on Confirmation Delete page");
        popupAlert.clickOKAlert();

        Logger.info("VP: There is a message \"Cannot delete page " + newPageNameChild1 + " since it has child page(s).\"");
        isExactALertMassage = popupAlert.verifyAlertMessage(wariningDeleteMessage);
//        assertTrue(isExactALertMassage, "Alert warning message is not displayed as expected");

        Logger.info("Step 11. Close confirmation dialog");
        popupAlert.closeAlert();

        Logger.info("Step 12. Go to the second page");
        homePage.moveMouseToPage(newPageNameParent);
        homePage.moveMouseToChildPage(newPageNameChild2);
        homePage.clickChildPage(newPageNameChild1, newPageNameChild2);

        Logger.info("Step 13. Click \"Delete\" link");
        homePage.moveToGlobalSetting();
        homePage.clickDeletePage();

        Logger.info("Step 14. Click Ok button on Confirmation Delete page");
        popupAlert.clickOKAlert();

        Logger.info("VP: " + newPageNameChild2 + " is deleted successfully");
        homePage.moveMouseToPage(newPageNameParent);
        isPageDisplay = homePage.verifyChildPageIsDisplayed(newPageNameParent,newPageNameChild2);
        assertFalse(isPageDisplay,newPageNameChild2+" is displayed as not expected");

        Logger.info("Post-Condition");

    }


}
