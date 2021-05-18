package tests;

import common.Utility;
import data.Repository;
import data.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;

import java.util.Arrays;
import java.util.List;

public class PanelTest extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage;
    private PagePopup pagePopup;
    private PanelPopup panelPopup;
    private PanelConfigurationPopup panelConfigurationPopup;
    private ChoosePanelPopup choosePanelPopup;
    private PanelPage panelPage;
    User user = new User();
    Repository repo = new Repository();

    @BeforeMethod
    private void beforeMethodPanelTest() {
        System.out.println("Run beforeMethod Panel Test");

        Logger.info("Step 2. Log in specific repository with valid account");
        homePage = loginPage.login(repo, user);
    }

    //    @Test(description = "Verify that when \"Choose panels\" form is expanded all pre-set panels are populated and sorted correctly ")
    public void DA_PANEL_TC027() {
        String newPageName = Utility.randomString(5);
        String newPanelPage = "zbox";
        List<String> list = Arrays.asList(
                "Action Implementation By Status",
                "Test Case Execution Failure Trend",
                "Test Case Execution Results",
                "Test Case Execution Trend",
                "Test Module Execution Failure Trend",
                "Test Module Execution Results",
                "Test Module Execution Trend",
                "Test Module Implementation By Priority",
                "Test Module Implementation By Status",
                "Test Module Status per Assigned Users",
                "zbox",
                "Test Case Execution",
                "Test Module Execution",
                "Test Objective Execution",
                "Test Module Execution Results Report"
                , "Test Case Execution History",
                "Test Module Execution History");

        Logger.info("DA_MP_TC27 - Verify that when \"Choose panels\" form is expanded all pre-set panels are populated and sorted correctly ");
        Logger.info("Step 3. Go to Global Setting -> Add page");
        homePage.moveMouseToGlobalSetting();
        pagePopup = homePage.clickAddNewPage();

        Logger.info("Step 4. Enter page name to Page Name field.");
        pagePopup.enterRenamePageName(newPageName);

        Logger.info("Step 5. Click OK button");
        homePage = pagePopup.clickOK();

        Logger.info("Step 6. Go to Global Setting -> Create Panel");
        homePage.moveMouseToGlobalSetting();
        panelPopup = homePage.clickCreatePanel();

        Logger.info("Step 7. Enter Panel name into Display Name textbox");
        panelPopup.enterDisplayName(newPanelPage);

        Logger.info("Step 8. Select any value in Series* dropdown list -> Click Ok button");
        panelPopup.selectRandomSeries();
        panelConfigurationPopup = panelPopup.clickOK();

        Logger.info("Step 9. Click Ok button in Panel Configuration popup");
        homePage = panelConfigurationPopup.clickOK();

        Logger.info("Step 10. Click on Choose Panel menu icon next to Global Setting icon");
        choosePanelPopup = homePage.clickPanelSetting();

        Logger.info("VP: Verify that all pre-set panels are populated and sorted correctly");
        assertTrue(choosePanelPopup.arePanelsPopulatedAndSortedCorrectly(list), "All pre-set panels are NOT populated and sorted correctly");

        System.out.println("Post-Condition");
        homePage = choosePanelPopup.moveMouseToAdminister();
        panelPage = homePage.clickPanels();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
        homePage = panelPage.clickPage(newPageName);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();

    }

    //    @Test(description = "Verify that when \"Add New Panel\" form is on focused all other control/form is disabled or locked.")
    public void DA_PANEL_TC028() {
        Logger.info("DA_MP_TC28 - Verify that when \"Add New Panel\" form is on focused all other control/form is disabled or locked.");
        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step 4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step 5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step 6. Try to click other controls when Add New Panel dialog is opening");
        homePage.moveMouseToGlobalSetting();

        Logger.info("VP: Verify that all pre-set panels are populated and sorted correctly");
        assertFalse(homePage.isAddNewPageDisplayed(), "The New Page is displayed as not expected");
    }

    //    @Test(description = "Verify that user is unable to create new panel when (*) required field is not filled")
    public void DA_PANEL_TC029() {
        String warningMessage = "Display Name is a required field.";
        boolean isExactMessage;
        Logger.info("DA_MP_TC29 - Verify that user is unable to create new panel when (*) required field is not filled");
        Logger.info("Step 5. Click on Administer/Panels link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 6. Click on \"Add new\" link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Click on OK button");
        panelPopup.clickOK();

        Logger.info("VP: Verify that all pre-set panels are populated and sorted correctly");
        isExactMessage = panelPopup.isAlertMessageCorrect(warningMessage);
        assertTrue(isExactMessage, "The message is not displayed as expected.");
    }

    //    @Test(description = "Verify that no special character except '@' character is allowed to be inputted into \"Display Name\" field")
    public void DA_PANEL_TC30() {
        String errorMessage = "Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;";
        String displayName1 = Utility.randomString(5) + Utility.randomSpecialCharExA(1);
        String displayName2 = Utility.randomString(5) + "@";
        boolean isExactMessage, isPanelPageDisplayed;
        Logger.info("DA_MP_TC30 - Verify that no special character except '@' character is allowed to be inputted into \"Display Name\" field");
        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step 4. Click Panel link");
        panelPage = homePage.clickPanels();

        Logger.info("Step 5. Click Add New link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Enter value into Display Name field with special characters except \"@\"");
        panelPopup.enterDisplayName(displayName1);
        panelPopup.selectRandomSeries();

        Logger.info("Step 7. Click on OK button");
        panelPopup.clickOK();

        Logger.info("VP: Message \"Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;\" is displayed");
        isExactMessage = panelPopup.isAlertMessageCorrect(errorMessage);
        assertTrue(isExactMessage, "The message is not displayed as expected.");

        Logger.info("Step 9. Close Warning Message box");
        panelPopup.clickOKAlert();
        panelPage = panelPopup.clickCancelFromPanel();

        Logger.info("Step 10. Click Add New link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 11.Enter value into Display Name field with special character is @");
        panelPopup.enterDisplayName(displayName2);
        panelPopup.selectRandomSeries();
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("VP: The new panel is created");
        isPanelPageDisplayed = panelPage.isPanelNameDisplayed(displayName2);
        assertTrue(isPanelPageDisplayed, "The Panel is not displayed as expected.");

        System.out.println("Post-Condition");
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that correct panel setting form is displayed with corresponding panel type selected")
    public void DA_PANEL_TC31() {
        String errorMessage = "Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;";
        String displayName1 = Utility.randomString(5);
        boolean isExactMessage, isPanelPageDisplayed;
        Logger.info("DA_MP_TC31 - Verify that correct panel setting form is displayed with corresponding panel type selected");
        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click on Add new link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("VP: Verify that chart panel setting form is displayed with corresponding panel type selected");

    }

    @AfterMethod
    private void afterMethodPanelTest() {

    }

}
