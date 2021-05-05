package tests;

import common.Constant;
import common.Utility;
import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.PagePopup;
import page.Popup;

public class ManagePageTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    //    @Test
    //NEED TO FIX
    public void DA_MP_TC011() {
        User user = new User();
        loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage();

        homePage.moveToGlobalSetting();

        Assert.assertFalse(homePage.verifyAddNewPageIsDisplayed(), "The Add new page button is displayed as not expected.");


    }

    //    @Test
    public void DA_MP_TC012() {
        User user = new User();
        String newPageName = Utility.randomString(5);
        boolean isRightPageName = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .clickOK()
                .verifyNewPageDisplaysBesideOverview(newPageName);
        assertTrue(isRightPageName, "The New Page Name is not displayed as expected.");

        Logger.info("Delete new page");

        Logger.info("Post-Condition");
        homePage.moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }

    //    @Test
    public void DA_MP_TC013() {
        User user = new User();
        String newPageName1 = Utility.randomString(5);

        String newPageName2 = Utility.randomString(5);

        boolean isRightPosition = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName1)
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName2)
                .selectDisplayAfter(newPageName1)
                .clickOK()
                .verifyPageIsPositioned(newPageName1, newPageName2);

        assertTrue(isRightPosition, "The " + newPageName2 + " page is not positioned besides the " + newPageName1 + " page as expected.");

        Logger.info("Post-Condition");
        homePage.moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert()
                .clickPageBesideOverview()
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }

    //    @Test
    public void DA_MP_TC014() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName = Utility.randomString(5);
        boolean isNewPageDisplay = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .checkThePubblicCheckBox()
                .clickOK()
                .logout()
                .login(Constant.REPOSITORY, user2)
                .verifyNewPageDisplaysBesideOverview(newPageName);

        assertTrue(isNewPageDisplay, "The New Page Name is not displayed in another valid account.");

        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete new page");
        homePage.logout()
                .login(Constant.REPOSITORY, user)
                .clickPageBesideOverview()
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }

    //    @Test
    public void DA_MP_TC015() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        boolean isChildPageDisplay = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .checkThePubblicCheckBox()
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName2)
                .clickCmbParentPage()
                .selectParentPage(newPageName)
                .clickOK()
                .logout()
                .login(Constant.REPOSITORY, user2)
                .moveMouseToPage(newPageName)
                .verifyChildPageIsDisplayed(newPageName, newPageName2);
        System.out.println(isChildPageDisplay);


        assertFalse(isChildPageDisplay, "The Child Page is displayed as not expected in another valid account.");

        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete pages");
        homePage.logout()
                .login(Constant.REPOSITORY, user)
                .moveMouseToPage(newPageName)
                .clickChildPage(newPageName, newPageName2)
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert()
                .clickPageBesideOverview()
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }

    //    @Test
    public void DA_MP_TC016() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        PagePopup popup = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName2)
                .checkThePubblicCheckBox()
                .clickOK()
                .clickPage(newPageName)
                .moveToGlobalSetting()
                .clickEditPage();

        assertTrue(popup.verifyEditPopupIsDisplayed(), "Edit Popup is not displayed as expected.");

        popup.checkThePubblicCheckBox()
                .clickOK()
                .clickPage(newPageName2)
                .moveToGlobalSetting()
                .clickEditPage();

        assertTrue(popup.verifyEditPopupIsDisplayed(), "Edit Popup is not displayed as expected.");

        homePage = popup.uncheckThePubblicCheckBox()
                .clickOK()
                .moveToGlobalSetting()
                .logout()
                .login(Constant.REPOSITORY, user2);

        assertTrue(homePage.verifyPageIsVisible(newPageName), "Page is not visible as expected.");
        assertFalse(homePage.verifyPageIsVisible(newPageName2), "Page is visible as not expected.");

        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete new page");
        homePage.logout()
                .login(Constant.REPOSITORY, user)
                .clickPage(newPageName)
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert()
                .clickPageBesideOverview()
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }

    //    @Test
//    NEED TO FIX
    public void DA_MP_TC017() {
        User user = new User();
        User user2 = new User(Constant.USERNAME2, Constant.PASSWORD);
        String newPageName = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        Popup popup = loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName2)
                .clickCmbParentPage()
                .selectParentPage(newPageName)
                .clickOK()
                .clickPage(newPageName)
                .moveToGlobalSetting()
                .clickDeletePage();

        assertTrue(popup.verifyAlertMessage(Constant.ALERTCONFIRMDELETEPAGE), "Alert message is not displayed as expected.");

        popup.clickOKAlert();

        //Error with 2 PopUp
        assertTrue(popup.verifyWarningMessage(newPageName), "Alert warning message is not displayed as expected");


        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete new page");
//        homePage.logout()
//                .login(Constant.REPOSITORY, user)
//                .clickPage(newPageName)
//                .moveToGlobalSetting()
//                .clickDeletePage()
//                .clickOKAlert()
//                .clickPageBesideOverview()
//                .moveToGlobalSetting()
//                .clickDeletePage()
//                .clickOKAlert();
    }

    @Test
    public void DA_MP_TC018() {
        User user = new User();
        String newPageName = Utility.randomString(5);
        String newPageName2 = Utility.randomString(5);
        String newPageName3 = Utility.randomString(5);
        loginPage.login(Constant.REPOSITORY, user)
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName)
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName2)
                .clickCmbParentPage()
                .selectParentPage(newPageName)
                .clickOK()
                .moveToGlobalSetting()
                .clickAddNewPage()
                .enterPageName(newPageName3)
                .clickCmbParentPage()
                .selectParentPage(newPageName)
                .clickOK();


        Logger.info("Post-Condition");
        Logger.info("Loggin old account and delete new page");
        homePage.moveMouseToPage(newPageName)
                .clickChildPage(newPageName, newPageName3)
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert()
                .moveMouseToPage(newPageName)
                .clickChildPage(newPageName, newPageName2)
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert()
                .clickPageBesideOverview()
                .moveToGlobalSetting()
                .clickDeletePage()
                .clickOKAlert();
    }


}
