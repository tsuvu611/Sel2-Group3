package tests;

import common.Utility;
import data.Repository;
import data.User;
import enums.*;
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
    private AddNewPanelPopup addNewPanelPopup;
    private PanelConfigurationPopup panelConfigurationPopup;
    private ChoosePanelPopup choosePanelPopup;
    private PanelPage panelPage;
    private SelectFolderPopup selectFolderPopup;
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
        addNewPanelPopup = homePage.clickCreatePanel();

        Logger.info("Step 7. Enter Panel name into Display Name textbox");
        addNewPanelPopup.enterDisplayName(newPanelPage);

        Logger.info("Step 8. Select any value in Series* dropdown list -> Click Ok button");
        addNewPanelPopup.selectRandomSeries();
        panelConfigurationPopup = addNewPanelPopup.clickOK();

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
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Click on OK button");
        addNewPanelPopup.clickOK();

        Logger.info("VP: Verify that all pre-set panels are populated and sorted correctly");
        isExactMessage = addNewPanelPopup.isAlertMessageCorrect(warningMessage);
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
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Enter value into Display Name field with special characters except \"@\"");
        addNewPanelPopup.enterDisplayName(displayName1);
//        panelPopup.selectRandomSeries();

        Logger.info("Step 7. Click on OK button");
        addNewPanelPopup.clickOK();

        Logger.info("VP: Message \"Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;\" is displayed");
        isExactMessage = addNewPanelPopup.isAlertMessageCorrect(errorMessage);
        assertTrue(isExactMessage, "The message is not displayed as expected.");

        Logger.info("Step 9. Close Warning Message box");
        addNewPanelPopup.clickOKAlert();
        panelPage = addNewPanelPopup.clickCancelFromPanel();

        Logger.info("Step 10. Click Add New link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 11.Enter value into Display Name field with special character is @");
        addNewPanelPopup.enterDisplayName(displayName2);
//        panelPopup.selectRandomSeries();
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("VP: The new panel is created");
        isPanelPageDisplayed = panelPage.isPanelNameDisplayed(displayName2);
        assertTrue(isPanelPageDisplayed, "The Panel is not displayed as expected.");

        System.out.println("Post-Condition");
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    //    @Test(description = "Verify that correct panel setting form is displayed with corresponding panel type selected")
    public void DA_PANEL_TC31() {
        Logger.info("DA_MP_TC31 - Verify that correct panel setting form is displayed with corresponding panel type selected");
        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("VP: Verify that chart panel setting form is displayed with corresponding panel type selected");
        assertTrue(addNewPanelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Chart), "Chart setting is not displayed after Display Name as expected.");

        Logger.info("Step 6. Select Indicator type");
        addNewPanelPopup.selectType(RadioButtonType.Indicator);

        Logger.info("VP: Verify that indicator panel setting form is displayed with corresponding panel type selected");
        assertTrue(addNewPanelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Indicator), "Chart settings is not displayed after Display Name as expected.");
        Logger.info("Step 6. Select Indicator type");

        Logger.info("Step 8. Select Report type");
        addNewPanelPopup.selectType(RadioButtonType.Report);

        Logger.info("VP: Verify that indicator panel setting form is displayed with corresponding panel type selected");
        Logger.info("Report panel setting form is no longer available.\n" +
                "-> Remove this check");
        assertTrue(addNewPanelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Report), "Indicator settings is not displayed after Display Name as expected.");

        Logger.info("Step 10. Select Heat Maps type");
        addNewPanelPopup.selectType(RadioButtonType.HeatMap);

        Logger.info("VP: Verify that heatmap panel setting form is displayed with corresponding panel type selected");
        assertTrue(addNewPanelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.HeatMap), "Heat Map settings is not displayed after Display Name as expected.");

    }

    //    @Test(description = "Verify that user is not allowed to create panel with duplicated \"Display Name\"")
    public void DA_PANEL_TC32() {
        String displayName = Utility.randomString(5);
        String errorMessage = displayName + " already exists. Please enter a different name.";
        Logger.info("DA_MP_TC32 - Verify that user is not allowed to create panel with duplicated \"Display Name\"  ");
        Logger.info("Step 3. Click on Administer/Panels link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 5. Enter display name to \"Display name\" field.");
        addNewPanelPopup.enterDisplayName(displayName);
//        panelPopup.selectRandomSeries();

        Logger.info("Step 6. Click on OK button");
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("Step 7. Click on Add new link again.");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 8. Enter display name same with previous display name to \"display name\" field. ");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step 9. Click on OK button");
        addNewPanelPopup.clickOKFromPanel();

        Logger.info("VP: Warning message: \"Duplicated panel already exists. Please enter a different name.\" show up");
        assertTrue(addNewPanelPopup.isAlertMessageCorrect(errorMessage), "The alert Message is not displayed as expected.");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickOKAlert();
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    //    @Test(description = "Verify that \"Data Profile\" listing of \"Add New Panel\" and \"Edit Panel\" control/form are in alphabetical order")
    public void DA_PANEL_TC33() {
        String displayName = Utility.randomString(5);
        String errorMessage = displayName + " already exists. Please enter a different name.";
        Logger.info("DA_MP_TC33 - Verify that \"Data Profile\" listing of \"Add New Panel\" and \"Edit Panel\" control/form are in alphabetical order");
        Logger.info("Step 3. Click on Administer/Panels link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("VP. Verify that Data Profile list is in alphabetical order");
        assertTrue(addNewPanelPopup.isCmbDataProfileAlphaOrder(), "The Data Profile list is not in alphabetical order as expected.");

        Logger.info("Step 6. Enter a display name to display name field");
        addNewPanelPopup.enterDisplayName(displayName);
//        panelPopup.selectRandomSeries();

        Logger.info("Step 7. Click on OK button");
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("Step 8. Click on Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("VP. Verify that Data Profile list is in alphabetical order");
        assertTrue(addNewPanelPopup.isCmbDataProfileAlphaOrder(), "The Data Profile list is not in alphabetical order as expected.");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    //    @Test(description = "Verify that newly created data profiles are populated correctly under the \"Data Profile\" dropped down menu in  \"Add New Panel\" and \"Edit Panel\" control/form")
    public void DA_PANEL_TC34() {
        String displayName1 = Utility.randomString(5);
        String displayName2 = Utility.randomString(5);
        Logger.info("DA_MP_TC34 - Verify that newly created data profiles are populated correctly under the \"Data Profile\" dropped down menu in  \"Add New Panel\" and \"Edit Panel\" control/form");
        Logger.info("Step 3. Click on Administer/Panels link");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 5. Enter name to Name textbox");
        addNewPanelPopup.enterDisplayName(displayName1);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step 6. Click on OK button");
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("Step 7. Click on Administer/Panels link");
        panelPage.clickAdminister();
        panelPage.clickPanels();

        Logger.info("Step 8. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("VP. Verify that \"" + displayName1 + "\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        assertTrue(addNewPanelPopup.isDataProfileContain(displayName1), displayName1 + "\" data profiles are NOT populated correctly under the \"Data Profile\" dropped down menu as expected.");

        Logger.info("Step 10. Enter name to Name textbox");
        addNewPanelPopup.enterDisplayName(displayName2);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step 11. Click on OK button");
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("Step 12. Click on Edit link");
        panelPage.clickPanelEdit(displayName2);

        Logger.info("VP. Verify that \"" + displayName1 + "\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        assertTrue(addNewPanelPopup.isDataProfileContain(displayName1), displayName1 + "\" data profiles are NOT populated correctly under the \"Data Profile\" dropped down menu as expected.");
    }

    //    @Test(description = "Verify that no special character except '@' character is allowed to be inputted into \"Chart Title\" field")
    public void DA_PANEL_TC35() {
        String displayName = Utility.randomString(5);
        String displayName1 = Utility.randomString(5) + "@";
        String chartTitleName1 = Utility.randomString(5) + Utility.randomSpecialCharExA(1);
        String chartTitleName2 = Utility.randomString(5) + "@";
        String errorMsg = "Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;";
        Logger.info("DA_MP_TC35 - Verify that no special character except '@' character is allowed to be inputted into \"Chart Title\" field");
        Logger.info("Step 3. Click on Administer");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 4. Click Panel link");
        panelPage = homePage.clickPanels();

        Logger.info("Step 5. Click on Add new link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Enter value into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step . Enter value into Chart Title field with special characters except \"@\"");
        addNewPanelPopup.enterChartTitle(chartTitleName1);

        Logger.info("Step 7. Click on OK button");
        addNewPanelPopup.clickOKFromPanel();

        Logger.info("VP: Message \"Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;\" is displayed");
        assertTrue(addNewPanelPopup.isAlertMessageCorrect(errorMsg), "Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%; is not displayed as expected.");

        Logger.info("Step 9. Close Warning Message box");
        addNewPanelPopup.closeAlert();
        panelPage = addNewPanelPopup.clickCancelFromPanel();

        Logger.info("Step 10. Click Add New link");
        panelPage.clickAddNewPage();

        Logger.info("Step 11. Enter value into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName1);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step 12. Enter value into Chart Title field with special characters except \"@\"");
        addNewPanelPopup.enterChartTitle(chartTitleName2);

        Logger.info("Step 7. Click on OK button");
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("VP. The new panel is created");
        assertTrue(panelPage.isPanelNameDisplayed(displayName1), "The new panel is NOT created as expected.");

        Logger.info("Post - Condition");
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    //    @Test(description = "Verify that all chart types ( Pie, Single Bar, Stacked Bar, Group Bar, Line ) are listed correctly under \"Chart Type\" dropped down menu.")
    public void DA_PANEL_TC36() {
        String pageName = Utility.randomString(5);
        List<String> list = Arrays.asList(
                "Pie",
                "Single Bar",
                "Stacked Bar",
                "Group Bar",
                "Line");
        Logger.info("DA_MP_TC36 - Verify that all chart types ( Pie, Single Bar, Stacked Bar, Group Bar, Line ) are listed correctly under \"Chart Type\" dropped down menu.");
        Logger.info("Step 5. Click 'Add Page' link");
        homePage.moveMouseToGlobalSetting();
        pagePopup = homePage.clickAddNewPage();

        Logger.info("Step 6. Enter Page Name");
        pagePopup.enterPageName(pageName);

        Logger.info("Step 7. Click 'OK' button");
        homePage = pagePopup.clickOK();

        Logger.info("Step 8. Click 'Choose Panels' button");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 9. Click 'Create new panel' button");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("VP: Check that 'Chart Type' are listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line'");
        assertTrue(addNewPanelPopup.isChartTypeList(list), "'Chart Type' are NOT listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line' as expected.");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        homePage = panelPage.clickPage(pageName);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    //    @Test(description = "Verify that \"Category\", \"Series\" and \"Caption\" field are enabled and disabled correctly corresponding to each type of the \"Chart Type\"")
    public void DA_PANEL_TC37() {
        String pageName = Utility.randomString(5);
        Logger.info("DA_MP_TC37 - Verify that \"Category\", \"Series\" and \"Caption\" field are enabled and disabled correctly corresponding to each type of the \"Chart Type\"");
        Logger.info("Step 5. Click 'Add Page' link");
        homePage.moveMouseToGlobalSetting();
        pagePopup = homePage.clickAddNewPage();

        Logger.info("Step 6. Enter Page Name");
        pagePopup.enterPageName(pageName);

        Logger.info("Step 7. Click 'OK' button");
        homePage = pagePopup.clickOK();

        Logger.info("Step 8. Click 'Choose Panels' button");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 9. Click 'Create new panel' button");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 11. Select 'Pie' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check that 'Category' and 'Caption' are disabled, 'Series' is enabled");
        assertTrue(addNewPanelPopup.isCategoryAndCaptionDisableSeriesEnable(), "'Category' and 'Caption' are NOT disabled, 'Series' is NOT enabled as expected.");

        Logger.info("Step 13. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 14. Select 'Single Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.SingleBar);

        Logger.info("VP: Check that 'Category' is disabled, 'Series' and 'Caption' are enabled");
        assertTrue(addNewPanelPopup.isCategoryDisableSeriesAndCaptionEnable(), "'Category' is NOT disabled, 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 16. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 17. Select 'Stacked Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.StackedBar);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(addNewPanelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 19. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 20. Select 'Group Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.GroupBar);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(addNewPanelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 21. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 22. Select 'Line' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.Line);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(addNewPanelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        homePage = panelPage.clickPage(pageName);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    //    @Test(description = "Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"2D\" and \"3D\" radio buttons")
    public void DA_PANEL_TC38() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String chartTitle = Utility.randomString(5);
        ChartType chartType = ChartType.StackedBar;
        DataProfile dataProfile = DataProfile.TestCaseExecution;
        CheckBoxState checkBoxState = CheckBoxState.ON;
        RadioButtonLegends radioButtonLegends = RadioButtonLegends.Top;
        RadioButtonStyle radioButtonStyle1 = RadioButtonStyle.Style3D;
        RadioButtonStyle radioButtonStyle2 = RadioButtonStyle.Style2D;
        Logger.info("DA_MP_TC38 - Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"2D\" and \"3D\" radio buttons");
        Logger.info("Step 5. Click 'Add Page' link");
        homePage.moveMouseToGlobalSetting();
        pagePopup = homePage.clickAddNewPage();

        Logger.info("Step 6. Enter Page Name");
        pagePopup.enterPageName(pageName);

        Logger.info("Step 7. Click 'OK' button");
        homePage = pagePopup.clickOK();

        Logger.info("Step 8. Click 'Choose Panels' button");
        homePage.clickAdminister();
        panelPage = homePage.clickPanels();

        Logger.info("Step 9. Click 'Create new panel' button");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        addNewPanelPopup.clickCmbChartType();

        Logger.info("Step 11. Select 'Pie' Chart Type");
        addNewPanelPopup.selectChartType(chartType);

        Logger.info("Step 12. Select 'Data Profile' drop-down menu");
        addNewPanelPopup.selectDataProfile(dataProfile);

        Logger.info("Step 13. Enter 'Display Name' and 'Chart Title'");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.enterChartTitle(chartTitle);

        Logger.info("Step 14. Select 'Show Title' checkbox");
        addNewPanelPopup.setStateCkbShowTitle(checkBoxState);

        Logger.info("Step 15. Select 'Legends' radio button");
        addNewPanelPopup.selectType(radioButtonLegends);

        Logger.info("Step 16. Select 'Style' radio button");
        addNewPanelPopup.selectType(radioButtonStyle1);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(addNewPanelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step . Select 'Style' radio button");
        addNewPanelPopup.selectType(radioButtonStyle2);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(addNewPanelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step 18. Click OK button");
//        panelPopup.selectRandomSeries();
//        panelPopup.selectCategorySeries();
        addNewPanelPopup.clickOKFromPanel();

        Logger.info("Select a page in drop-down menu");
//        panelPopup.clickPage(pageName);

        Logger.info("Step 22. Click 'Edit Panel' button of panel '" + displayName + "'");
        addNewPanelPopup = panelPage.clickPanelEdit(displayName);

        Logger.info("Step 23. Select 'Style' radio button");
        addNewPanelPopup.selectType(radioButtonStyle1);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(addNewPanelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step 25. Select 'Style' radio button");
        addNewPanelPopup.selectType(radioButtonStyle2);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(addNewPanelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
        homePage = panelPage.clickPage(pageName);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons")
    public void DA_PANEL_TC39() {
        String displayName = "";
        Logger.info("DA_MP_TC39 - Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons");

        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step 4. Click Panel link");
        panelPage = homePage.clickPanels();

        Logger.info("Step 5. Click Add New link");
        addNewPanelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Click None radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 8. Click top radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 10. Click Right radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 12. Click Bot radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 14. Click Left radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 16. Create a new panel");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        panelPage = addNewPanelPopup.clickOKFromPanel();

        Logger.info("Step 17. Click Edit Panel link");
        addNewPanelPopup = panelPage.clickPanelEdit(displayName);

        Logger.info("Step 18. Click None radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 20. Click top radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 22. Click Right radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 24. Click Bot radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 26. Click Left radio button for Legend");
        addNewPanelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all \"Data Labels\" check boxes are enabled and disabled correctly corresponding to each type of \"Chart Type\"")
    public void DA_PANEL_TC40() {
        String displayName = "";
        String pageName = Utility.randomString(5);
        Logger.info("DA_MP_TC40 - Verify that all \"Data Labels\" check boxes are enabled and disabled correctly corresponding to each type of \"Chart Type\"");

        Logger.info("Step 5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        pagePopup = homePage.clickAddNewPage();

        Logger.info("Step 6. Enter Page Name");
        pagePopup.enterPageName(pageName);

        Logger.info("Step7. Click Ok Button");
        homePage = pagePopup.clickOK();

        Logger.info("Step8. Click 'Choose Panels' button below '"+pageName+"' button");
        homePage.clickPanels();

        Logger.info("Step9. Click 'Create new panel' button");
        panelPage.clickAddNew();

        Logger.info("Step10. Click 'Chart Type' drop-down menu");
        Logger.info("Step11. Select 'Pie' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        assertFalse(addNewPanelPopup.isCKBCategoryEnable(),"Categories' checkbox is NOT disabled");
        assertTrue(addNewPanelPopup.isCKBSeriesEnable(),"Series' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBValueEnable(),"Value' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBPercentageEnable(),"Percentage' checkbox is NOT enable");

        Logger.info("Step13. Click 'Chart Type' drop-down menu");
        Logger.info("Step14. Select 'Single Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.SingleBar);

        Logger.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        assertFalse(addNewPanelPopup.isCKBCategoryEnable(),"Categories' checkbox is NOT disabled");
        assertTrue(addNewPanelPopup.isCKBSeriesEnable(),"Series' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBValueEnable(),"Value' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBPercentageEnable(),"Percentage' checkbox is NOT enable");

        Logger.info("Step16. Click 'Chart Type' drop-down menu");
        Logger.info("Step17. Select 'Stacked Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.StackedBar);

        Logger.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        assertTrue(addNewPanelPopup.isCKBCategoryEnable(),"Categories' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBSeriesEnable(),"Series' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBValueEnable(),"Value' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBPercentageEnable(),"Percentage' checkbox is NOT enable");

        Logger.info("Step19. Click 'Chart Type' drop-down menu");
        Logger.info("Step20. Select 'Group Bar' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.GroupBar);

        Logger.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        assertTrue(addNewPanelPopup.isCKBCategoryEnable(),"Categories' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBSeriesEnable(),"Series' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBValueEnable(),"Value' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBPercentageEnable(),"Percentage' checkbox is NOT enable");

        Logger.info("Step22. Click 'Chart Type' drop-down menu");
        Logger.info("Step23. Select 'Line' Chart Type");
        addNewPanelPopup.selectChartType(ChartType.Line);

        Logger.info("Check that 'Categories' checkbox is disabled, 'Series' checkbox, 'Value' checkbox and 'Percentage' checkbox are enabled");
        assertTrue(addNewPanelPopup.isCKBCategoryEnable(),"Categories' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBSeriesEnable(),"Series' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBValueEnable(),"Value' checkbox is NOT enable");
        assertTrue(addNewPanelPopup.isCKBPercentageEnable(),"Percentage' checkbox is NOT enable");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Data Labels\" check boxes buttons")
    public void DA_PANEL_TC41() {
        String displayName = "";
        String panelName = Utility.randomString(5);
        Logger.info("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Data Labels\" check boxes buttons");

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step6. Check Series checkbox for Data Labels");
        addNewPanelPopup.setStateCkbSeries(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step8. Uncheck Series checkbox");
        addNewPanelPopup.setStateCkbSeries(CheckBoxState.OFF);

        Logger.info("Step9. Check Value checkbox for Data Labels");
        addNewPanelPopup.setStateCkbValue(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Ste11. Uncheck Value checkbox");
        addNewPanelPopup.setStateCkbValue(CheckBoxState.OFF);

        Logger.info("Step12. Check Percentage checkbox for Data Labels");
        addNewPanelPopup.setStateCkbPercentage(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Ste14. Uncheck Percentage checkbox");
        addNewPanelPopup.setStateCkbPercentage(CheckBoxState.OFF);

        Logger.info("Step15. Create a new panel");
        addNewPanelPopup.enterDisplayName(panelName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();

        Logger.info("Step 16. Click Edit Panel link");
        panelPage.clickPanelEdit(panelName);

        Logger.info("Step17. Check Series checkbox for Data Labels");
        addNewPanelPopup.setStateCkbSeries(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step19. Uncheck Series checkbox");
        addNewPanelPopup.setStateCkbSeries(CheckBoxState.OFF);

        Logger.info("Step20. Check Value checkbox for Data Labels");
        addNewPanelPopup.setStateCkbValue(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step22. Uncheck Value checkbox");
        addNewPanelPopup.setStateCkbValue(CheckBoxState.OFF);

        Logger.info("Step23. Check Percentage checkbox for Data Labels");
        addNewPanelPopup.setStateCkbPercentage(CheckBoxState.ON);

        Logger.info("StepVP. Observe the current page");
        assertFalse(addNewPanelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all pages are listed correctly under the \"Select page *\" dropped down menu of \"Panel Configuration\" form/ control")
    public void DA_PANEL_TC42() {
        String displayName = "";
        String pageName1 = Utility.randomString(5);
        String pageName2 = Utility.randomString(5);
        String pageName3 = Utility.randomString(5);
        Logger.info("Verify that all pages are listed correctly under the \"Select page *\" dropped down menu of \"Panel Configuration\" form/ control");

        Logger.info("Step5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step6. Enter Page Name");
        pagePopup.enterPageName(pageName1);

        Logger.info("Step7. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step8. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step9. Enter Page Name");
        pagePopup.enterPageName(pageName2);

        Logger.info("Step10. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step11. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step12. Enter Page Name");
        pagePopup.enterPageName(pageName3);

        Logger.info("Step13. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step14. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step15. Click on any Chart panel instance");
        choosePanelPopup.clickAction();

        Logger.info("Step16. Click 'Select Page*' drop-down menu");
        panelConfigurationPopup.clickCmbSelectPage();

        Logger.info("VP:Check that 'Select Page*' drop-down menu contains 3 items: '"+pageName1+"', '"+pageName2+"' and '"+pageName3+"'");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName1),"'Select Page*' drop-down menu NOT contains 3 items");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName2),"'Select Page*' drop-down menu NOT contains 3 items");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName3),"'Select Page*' drop-down menu NOT contains 3 items");

        Logger.info("Post - Condition");
        panelConfigurationPopup.clickOK();
        homePage.clickPage(pageName1);
        homePage.clickDeletePage();
        homePage.clickOKAlert();
        homePage.clickPage(pageName2);
        homePage.clickDeletePage();
        homePage.clickOKAlert();
        homePage.clickPage(pageName3);
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that only integer number inputs from 300-800 are valid for \"Height *\" field ")
    public void DA_PANEL_TC43() {
        String displayName = "";
        String pageName1 = Utility.randomString(5);
        String pageName2 = Utility.randomString(5);
        String pageName3 = Utility.randomString(5);
        Logger.info("Verify that only integer number inputs from 300-800 are valid for \"Height *\" field ");

        Logger.info("Step5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step6. Enter Page Name");
        pagePopup.enterPageName(pageName1);

        Logger.info("Step7. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step8. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step9. Enter Page Name");
        pagePopup.enterPageName(pageName2);

        Logger.info("Step10. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step11. Click 'Choose panels' button");
        homePage.moveMouseToAdminister();
        homePage.clickPanelSetting();

        Logger.info("Step12. Enter Page Name");
        pagePopup.enterPageName(pageName3);

        Logger.info("Step13. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step14. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step15. Click on any Chart panel instance");
        choosePanelPopup.clickAction();

        Logger.info("Step16. Click 'Select Page*' drop-down menu");
        panelConfigurationPopup.clickCmbSelectPage();

        Logger.info("VP:Check that 'Select Page*' drop-down menu contains 3 items: '"+pageName1+"', '"+pageName2+"' and '"+pageName3+"'");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName1),"'Select Page*' drop-down menu NOT contains 3 items");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName2),"'Select Page*' drop-down menu NOT contains 3 items");
        assertTrue(panelConfigurationPopup.isCmbSelectContain(pageName3),"'Select Page*' drop-down menu NOT contains 3 items");

        Logger.info("Post - Condition");
        panelPage = addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that \"Height *\" field is not allowed to be empty ")
    public void DA_PANEL_TC44() {
        String displayName = "";
        String pageName1 = Utility.randomString(5);
        Logger.info("Verify that \"Height *\" field is not allowed to be empty ");

        Logger.info("Step5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step6. Enter Page Name");
        pagePopup.enterPageName(pageName1);

        Logger.info("Step7. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step11. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step12. Click on any Chart panel instance");
        choosePanelPopup.clickAction();

        Logger.info("Step13. Leave 'Height *' field empty");
        panelConfigurationPopup.enterHeight("");

        Logger.info("Step14.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Check that 'Panel height is required field' message display");
        assertTrue(panelConfigurationPopup.isAlertMessageCorrect("Panel height is a required field."),"'\"Panel height is a required field.' message NOt display ");

        Logger.info("Post - Condition");
        panelConfigurationPopup.clickOKAlert();
        panelConfigurationPopup.clickCancel();
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that \"Folder\" field is not allowed to be empty ")
    public void DA_PANEL_TC45() {
        String displayName = "";
        String pageName1 = Utility.randomString(5);
        Logger.info("Verify that \"Folder\" field is not allowed to be empty ");

        Logger.info("Step5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step6. Enter Page Name");
        pagePopup.enterPageName(pageName1);

        Logger.info("Step7. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step11. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step12. Click on any Chart panel instance");
        choosePanelPopup.clickAction();

        Logger.info("Step13. Leave 'Folder *' field empty");
        panelConfigurationPopup.enterTxtFolder("");

        Logger.info("Step14.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Check that 'Panel folder is incorrect' message display");
        assertTrue(panelConfigurationPopup.isAlertMessageCorrect("Panel height is a required field."),"'\"Panel folder is incorrect.' message NOt display ");

        Logger.info("Post - Condition");
        panelConfigurationPopup.clickOKAlert();
        panelConfigurationPopup.clickCancel();
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that only valid folder path of corresponding item type ( e.g. Actions, Test Modules) are allowed to be entered into \"Folder\" field ")
    public void DA_PANEL_TC46() {
        String invalidFolderPath = "hahaha";
        String validFolderPath = "/Car Rental/Actions";
        String pageName1 = Utility.randomString(5);
        Logger.info("Verify that only valid folder path of corresponding item type ( e.g. Actions, Test Modules) are allowed to be entered into \"Folder\" field ");

        Logger.info("Step5. Click 'Add Page' button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step6. Enter Page Name");
        pagePopup.enterPageName(pageName1);

        Logger.info("Step7. Click 'OK' button");
        pagePopup.clickOK();

        Logger.info("Step11. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step12. Click on any Chart panel instance");
        choosePanelPopup.clickAction();

        Logger.info("Step13. Enter invalid folder path");
        panelConfigurationPopup.enterTxtFolder(invalidFolderPath);

        Logger.info("Step14.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Check that 'Panel folder is incorrect' message display");
        assertTrue(panelConfigurationPopup.isAlertMessageCorrect("Panel folder is incorrect."),"\"Panel folder is incorrect\"' message NOt display ");

        Logger.info("Step14. Enter valid folder path");
        panelConfigurationPopup.enterTxtFolder(validFolderPath);

        Logger.info("Step15.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("Post - Condition");
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is able to navigate properly to folders with \"Select Folder\" form")
    public void DA_PANEL_TC47() {
        String pageName1 = Utility.randomString(5);
        String panelName = Utility.randomString(5);
        Logger.info("Verify that user is able to navigate properly to folders with \"Select Folder\" form");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName1);
        pagePopup.clickOK();

        Logger.info("Step4. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step5. Click Create New Panel button");
        choosePanelPopup.clickBtnCreateNewPanel();

        Logger.info("Step6 .Enter all required fields on Add New Panel page");
        addNewPanelPopup.enterDisplayName(panelName);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step7. Click Ok button");
        addNewPanelPopup.clickOK();

        Logger.info("Step8. Click Select Folder button on Panel Configuration dialog");
        panelConfigurationPopup.clickBtnFolder();

        Logger.info("Step9. Choose folder name in Folder Form");
        selectFolderPopup.selectRandomFolder();

        Logger.info("Step10. Click Ok button on Select Folder form");
        selectFolderPopup.clickOK();

        Logger.info("VP.User is able to select properly folder with Select Folder form");
        assertTrue(selectFolderPopup.isSelectFolderDisplayed(),"User is NOT able to select properly folder with Select Folder form");

        Logger.info("Step15.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("Post - Condition");
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that population of corresponding item type ( e.g. Actions, Test Modules) folders is correct in \"Select Folder form")
    public void DA_PANEL_TC48() {
        String pageName1 = Utility.randomString(5);
        String panelName = Utility.randomString(5);
        Logger.info("Verify that population of corresponding item type ( e.g. Actions, Test Modules) folders is correct in \"Select Folder form");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName1);
        pagePopup.clickOK();

        Logger.info("Step4. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step5. Click Create New Panel button");
        choosePanelPopup.clickBtnCreateNewPanel();

        Logger.info("Step6 .Enter all required fields on Add New Panel page");
        addNewPanelPopup.enterDisplayName(panelName);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step7. Click Ok button");
        addNewPanelPopup.clickOK();

        Logger.info("Step8. Click Select Folder button on Panel Configuration dialog");
        panelConfigurationPopup.clickBtnFolder();

        Logger.info("Step9. Choose folder name in Folder Form");
        selectFolderPopup.selectRandomFolder();

        Logger.info("Step10. Click Ok button on Select Folder form");
        selectFolderPopup.clickOK();

        Logger.info("VP.This VP is incomprehension");


        Logger.info("Post - Condition");
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that all folder paths of corresponding item type ( e.g. Actions, Test Modules) are correct in \"Select Folder\" form ")
    public void DA_PANEL_TC49() {
        String pageName1 = Utility.randomString(5);
        String panelName = Utility.randomString(5);
        Logger.info("Verify that all folder paths of corresponding item type ( e.g. Actions, Test Modules) are correct in \"Select Folder\" form ");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName1);
        pagePopup.clickOK();

        Logger.info("Step4. Click 'Choose panels' button");
        homePage.clickPanelSetting();

        Logger.info("Step5. Click Create New Panel button");
        choosePanelPopup.clickBtnCreateNewPanel();

        Logger.info("Step6 .Enter all required fields on Add New Panel page");
        addNewPanelPopup.enterDisplayName(panelName);
        addNewPanelPopup.selectRandomSeries();

        Logger.info("Step7. Click Ok button");
        addNewPanelPopup.clickOK();

        Logger.info("Step8. Click Select Folder button on Panel Configuration dialog");
        panelConfigurationPopup.clickBtnFolder();

        Logger.info("Step9. Choose folder name in Folder Form");
        selectFolderPopup.selectRandomFolder();

        Logger.info("Step10. Click Ok button on Select Folder form");
        selectFolderPopup.clickOK();

        Logger.info("VP.Folder path is displayed correctly after selecting folder in Select Folder form");
        assertTrue(panelConfigurationPopup.isCorrectFolderPath(),"Folder path is NOT displayed correctly after selecting folder in Select Folder form");

        Logger.info("Step15.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("Post - Condition");
        homePage.clickPage(pageName1);
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is able to successfully edit \"Display Name\" of any Panel providing that the name is not duplicated with existing Panels' name")
    public void DA_PANEL_TC50() {
        String displayName = Utility.randomString(5);
        Logger.info("Verify that user is able to successfully edit \"Display Name\" of any Panel providing that the name is not duplicated with existing Panels' name");

        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step6 .Enter a valid name into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);

        Logger.info("VP.The new panel is created successfully");
        assertTrue(addNewPanelPopup.isAddnewPanelPopUpDisplayed(),"The new panel NOT is created successfully");

        Logger.info("Step15.Click OK button");
        panelConfigurationPopup.clickOK();

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is unable to change \"Display Name\" of any Panel if there is special character except '@' inputted")
    public void DA_PANEL_TC51() {
        String displayName = Utility.randomString(5);
        String CharacterDisplayName = "&&##"+Utility.randomString(5);
        Logger.info("Verify that user is unable to change \"Display Name\" of any Panel if there is special character except '@' inputted");

        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step6 .Enter a valid name into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();

        Logger.info("Step7 Click Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("Step8: Edit panel name with special characters");
        addNewPanelPopup.enterDisplayName(CharacterDisplayName);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\"#[]{}=%;");
        assertTrue(addNewPanelPopup.isAlertMessageCorrect("Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\"#[]{}=%;"),"The new panel NOT is created successfully");

        Logger.info("Step11.Close warning message box");
        addNewPanelPopup.clickOKAlert();

        Logger.info("Step12. Click Edit link");
        //Wrong Step...

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is unable to edit  \"Height *\" field to anything apart from integer number with in 300-800 range")
    public void DA_PANEL_TC52() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        Logger.info("Verify that user is unable to edit  \"Height *\" field to anything apart from integer number with in 300-800 range");

        Logger.info("Step4. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName);
        pagePopup.clickOK();

        Logger.info("Step4. Click Choose Panel button");
        homePage.clickPanelSetting();

        Logger.info("Step5. Click Create New Panel button");
        choosePanelPopup.clickCreatePanel();

        Logger.info("Step6 .Enter all required fields on Add New Panel page");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();

        Logger.info("Step8 Enter invalid height into Height field");
        panelConfigurationPopup.enterHeight("200");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Panel height must be greater than or equal to 300 and less than or equal to 800.");
        assertTrue(panelConfigurationPopup.isAlertMessageCorrect("Panel height must be greater than or equal to 300 and less than or equal to 800."),"Panel height must be greater than or equal to 300 and less than or equal to 800.");

        Logger.info("Step11.Close warning message box");
        addNewPanelPopup.clickOKAlert();

        Logger.info("Step12 Enter valid height into Height field");
        panelConfigurationPopup.enterHeight("400");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.User is able to edit Height field to anything apart from integer number with in 300-800 range.");
        assertFalse(panelConfigurationPopup.isPanelConfigDisplayed(),"User is NOT able to edit Height field to anything apart from integer number with in 300-800 range");

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is able to successfully edit \"Folder\" field with valid path")
    public void DA_PANEL_TC54() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String validFolderPath = "/Car Rental/Actions";
        Logger.info("Verify that user is able to successfully edit \"Folder\" field with valid path");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName);
        pagePopup.clickOK();

        Logger.info("Step4. Create a new panel");
        homePage.clickPanelSetting();
        choosePanelPopup.clickCreatePanel();
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();
        panelConfigurationPopup.clickOK();

        Logger.info("Step 5 Click Choose Panel button");
        homePage.clickPanelSetting();

        Logger.info("Step 6 Click on the newly created panel link");
        choosePanelPopup.clickNewlyPanelLink();

        Logger.info("Step7 Edit valid folder path");
        panelConfigurationPopup.enterTxtFolder(validFolderPath);

        Logger.info("Step8 ClickOK Button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.User is able to successfully edit \"Folder\" field with valid path.");
        assertFalse(panelConfigurationPopup.isPanelConfigDisplayed(),"User is able to successfully edit \"Folder\" field with valid path.");

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is unable to edit \"Folder\" field with invalid path")
    public void DA_PANEL_TC55() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String invalidFolderPath = "haha";
        Logger.info("Verify that user is unable to edit \"Folder\" field with invalid path");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName);
        pagePopup.clickOK();

        Logger.info("Step4. Create a new panel");
        homePage.clickPanelSetting();
        choosePanelPopup.clickCreatePanel();
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();
        panelConfigurationPopup.clickOK();

        Logger.info("Step 5 Click Choose Panel button");
        homePage.clickPanelSetting();

        Logger.info("Step 6 Click on the newly created panel link");
        choosePanelPopup.clickNewlyPanelLink();

        Logger.info("Step7 Edit valid folder path");
        panelConfigurationPopup.enterTxtFolder("");

        Logger.info("Step8 ClickOK Button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Verify that user is unable to edit \"Folder\" field with empty value.");
        assertTrue(panelConfigurationPopup.isPanelConfigDisplayed(),"Verify that user is unable to edit \"Folder\" field with empty value.");

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is unable to edit \"Folder\" field with empty value")
    public void DA_PANEL_TC56() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String invalidFolderPath = "haha";
        Logger.info("Verify that user is unable to edit \"Folder\" field with empty value");

        Logger.info("Step3. Create a new page");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();
        pagePopup.enterPageName(pageName);
        pagePopup.clickOK();

        Logger.info("Step4. Create a new panel");
        homePage.clickPanelSetting();
        choosePanelPopup.clickCreatePanel();
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();
        panelConfigurationPopup.clickOK();

        Logger.info("Step 5 Click Choose Panel button");
        homePage.clickPanelSetting();

        Logger.info("Step 6 Click on the newly created panel link");
        choosePanelPopup.clickNewlyPanelLink();

        Logger.info("Step7 Edit valid folder path");
        panelConfigurationPopup.enterTxtFolder("");

        Logger.info("Step8 ClickOK Button");
        panelConfigurationPopup.clickOK();

        Logger.info("VP.Verify that user is unable to edit \"Folder\" field with empty value.");
        assertTrue(panelConfigurationPopup.isPanelConfigDisplayed(),"Verify that user is unable to edit \"Folder\" field with empty value.");

        Logger.info("Post - Condition");
        homePage.moveMouseToGlobalSetting();
        homePage.clickDeletePage();
        homePage.clickOKAlert();
    }

    @Test(description = "Verify that user is able to successfully edit \"Chart Type\"")
    public void DA_PANEL_TC57() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String invalidFolderPath = "haha";
        Logger.info("Verify that user is able to successfully edit \"Chart Type\"");


        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step6 .Enter a valid name into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectChartType(ChartType.Pie);
        addNewPanelPopup.clickOK();

        Logger.info("Step7. Click Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("Step8.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.SingleBar);
        addNewPanelPopup.clickOK();

        Logger.info("VP.User is able to edit Chart Type successfully.");
        assertFalse(addNewPanelPopup.isAddnewPanelPopUpDisplayed(),"User is able to edit Chart Type successfully");

        Logger.info("Post - Condition");
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "VerifVerify that user is able to successfully edit \"Chart Type\"")
    public void DA_PANEL_TC58() {
        String pageName = Utility.randomString(5);
        String displayName = Utility.randomString(5);
        String invalidFolderPath = "haha";
        Logger.info("Verify that user is able to successfully edit \"Chart Type\"");


        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step6 .Enter a valid name into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectChartType(ChartType.Pie);
        addNewPanelPopup.clickOK();

        Logger.info("Step7. Click Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("Step8.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.Pie);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Observe the current page.");
        assertFalse(addNewPanelPopup.isCaptionCategoryEnable(),"Category and Caption are NOT disabled");
        assertTrue(addNewPanelPopup.isSeriesEnable(),"Series is NOT enabled");

        Logger.info("Step10.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.SingleBar);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Observe the current page.");
        assertFalse(addNewPanelPopup.isCategoryEnable(),"Category and Caption are NOT disabled");
        assertTrue(addNewPanelPopup.isCaptionSeriesEnable(),"Series and Caption is NOT enabled");

        Logger.info("Step12.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.StackedBar);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Observe the current page.");
        assertTrue(addNewPanelPopup.isCaptionSeriesEnable(),"Series and Caption is NOT enabled");
        assertTrue(addNewPanelPopup.isCategoryEnable(),"Category is NOT enabled");

        Logger.info("Step14.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.GroupBar);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Observe the current page.");
        assertTrue(addNewPanelPopup.isCaptionSeriesEnable(),"Series and Caption is NOT enabled");
        assertTrue(addNewPanelPopup.isCategoryEnable(),"Category is NOT enabled");

        Logger.info("Step14.Change Chart Type for panel");
        addNewPanelPopup.selectChartType(ChartType.Line);
        addNewPanelPopup.clickOK();

        Logger.info("VP.Observe the current page.");
        assertTrue(addNewPanelPopup.isCaptionSeriesEnable(),"Series and Caption is NOT enabled");
        assertTrue(addNewPanelPopup.isCategoryEnable(),"Category is NOT enabled");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"2D\" and \"3D\" radio buttons in \"Edit Panel\" form")
    public void DA_PANEL_TC59() {
        String displayName = Utility.randomString(5);
        Logger.info("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"2D\" and \"3D\" radio buttons in \"Edit Panel\" form");

        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step 6. Switch between \"2D\" and \"3D\"");
        addNewPanelPopup.selectType(RadioButtonStyle.Style2D);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 5. Switch between \"2D\" and \"3D\"");
        addNewPanelPopup.selectType(RadioButtonStyle.Style3D);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step8 .Create a new panel");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectChartType(ChartType.Pie);
        addNewPanelPopup.clickOK();

        Logger.info("Step9. Click Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("Step 10. Switch between \"2D\" and \"3D\"");
        addNewPanelPopup.selectType(RadioButtonStyle.Style2D);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 10. Switch between \"2D\" and \"3D\"");
        addNewPanelPopup.selectType(RadioButtonStyle.Style3D);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("VP.Observe the current page.");
        assertFalse(addNewPanelPopup.isCaptionCategoryEnable(),"Category and Caption are NOT disabled");
        assertTrue(addNewPanelPopup.isSeriesEnable(),"Series is NOT enabled");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons in \"Edit Panel\" form")
    public void DA_PANEL_TC60() {
        String displayName = Utility.randomString(5);
        Logger.info("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons in \"Edit Panel\" form");

        Logger.info("Step3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step4. Click Panel link");
        homePage.clickPanels();

        Logger.info("Step5. Click Add New link");
        panelPage.clickAddNew();

        Logger.info("Step 6. Click None radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 7. Click Top radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 10. Click Right radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 12. Click Bottom radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 14. Click Left radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step16 .Enter a valid name into Display Name field");
        addNewPanelPopup.enterDisplayName(displayName);
        addNewPanelPopup.selectRandomSeries();
        addNewPanelPopup.clickOK();

        Logger.info("Step17. Click Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("Step 18. Click None radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 20. Click Top radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 22. Click Right radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 24. Click Bottom radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");

        Logger.info("Step 26. Click Left radio button for Legends");
        addNewPanelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings is unchanged in the Add New Panel form");
        assertFalse(addNewPanelPopup.areAllSettingChanged(),"settings is unchanged in the Add New Panel form");


        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that correct values are populated for corresponding parameters under \"Categories\" and \"Series\" field ( e.g. Priority: High, Status : Completed)")
    public void DA_PANEL_TC61() {
        String displayName = Utility.randomString(5);
        Logger.info("Verify that correct values are populated for corresponding parameters under \"Categories\" and \"Series\" field ( e.g. Priority: High, Status : Completed)");

        Logger.info("Step3. Click Choose Panels button");
        homePage.clickPanelSetting();

        Logger.info("Step 4.Click Test Module Implementation By Priority link");
        choosePanelPopup.clickElmImplementByPriority();

        Logger.info("Step 5. Click Ok button on Panel Configuration dialog");
        panelConfigurationPopup.clickOK();

        Logger.info("Step 6. -----OUT OF DATE TESTCASE----");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that all changes made to or with the values populated for corresponding parameters under \"Categories\" and \"Series\" field in Edit Panel are recorded correctly")
    public void DA_PANEL_TC62() {
        String captionCategory = Utility.randomString(5);
        String captionSeries = Utility.randomString(5);
        Logger.info("Verify that all changes made to or with the values populated for corresponding parameters under \"Categories\" and \"Series\" field in Edit Panel are recorded correctlyVerify that correct values are populated for corresponding parameters under \"Categories\" and \"Series\" field ( e.g. Priority: High, Status : Completed)");

        Logger.info("Step3. Click Choose Panels button");
        homePage.clickPanelSetting();

        Logger.info("Step 4.Click Test Module Implementation By Priority link");
        choosePanelPopup.clickElmImplementByPriority();

        Logger.info("Step 5. Click Ok button on Panel Configuration dialog");
        panelConfigurationPopup.clickOK();

        Logger.info("Step 6. Click Edit Panel icon");
        homePage.clickEdit();

        Logger.info("Step 7.Enter value into Caption field for Category");
        addNewPanelPopup.enterTxtCaptionCategory(captionCategory);

        Logger.info("Step 8.Enter value into Caption field for Series");
        addNewPanelPopup.enterTxtCaptionSeries(captionSeries);

        Logger.info("Step 9. Click Ok button");
        addNewPanelPopup.clickOK();

        Logger.info("Step 10. Click Edit Panel icon");
        homePage.clickEdit();

        Logger.info("VP. Caption's value are saved");
        assertTrue(addNewPanelPopup.isCorrectCaptionCategory(captionCategory),"Caption Category Not Save");
        assertTrue(addNewPanelPopup.isCorrectCaptionSeries(captionCategory),"Caption Category Not Save");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that for \"Action Implementation By Status\" panel instance, when user changes from \"Pie\" chart to any other chart type then change back the \"Edit Panel\" form should be as original")
    public void DA_PANEL_TC63() {
        Logger.info("Verify that for \"Action Implementation By Status\" panel instance, when user changes from \"Pie\" chart to any other chart type then change back the \"Edit Panel\" form should be as original");

        Logger.info("Step3. Click Choose Panels button");
        homePage.clickPanelSetting();

        Logger.info("Step 4.Click Test Module Implementation By Priority link");
        choosePanelPopup.clickElmImplementByPriority();

        Logger.info("Step 5. Click Ok button on Panel Configuration dialog");
        panelConfigurationPopup.clickOK();

        Logger.info("Step 6. Click Edit Panel icon");
        homePage.clickEdit();

        Logger.info("Step 7.Click on Chart Type dropped down menu");
        Logger.info("Step 8.Select Single Bar");
        addNewPanelPopup.selectChartType(ChartType.SingleBar);

        Logger.info("Step 9.Click on Chart Type dropped down menu");
        Logger.info("Step 10.Select Single Bar");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check original \"Pie\" - Edit Panel form is displayed");
        assertTrue(addNewPanelPopup.isCorrectChartType(ChartType.Pie),"Check original \"Pie\" - Edit Panel form is NOT displayed");

        Logger.info("Step 14.Click on Chart Type dropped down menu");
        Logger.info("Step 15.Select Stacked Bar");
        addNewPanelPopup.selectChartType(ChartType.StackedBar);

        Logger.info("Step 9.Click on Chart Type dropped down menu");
        Logger.info("Step 10.Select Single Bar");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check original \"Pie\" - Edit Panel form is displayed");
        assertTrue(addNewPanelPopup.isCorrectChartType(ChartType.Pie),"Check original \"Pie\" - Edit Panel form is NOT displayed");

        Logger.info("Step 7.Click on Chart Type dropped down menu");
        Logger.info("Step 8.Select Group Bar");
        addNewPanelPopup.selectChartType(ChartType.GroupBar);

        Logger.info("Step 9.Click on Chart Type dropped down menu");
        Logger.info("Step 10.Select Single Bar");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check original \"Pie\" - Edit Panel form is displayed");
        assertTrue(addNewPanelPopup.isCorrectChartType(ChartType.Pie),"Check original \"Pie\" - Edit Panel form is NOT displayed");

        Logger.info("Step 14.Click on Chart Type dropped down menu");
        Logger.info("Step 15.Select Line Bar");
        addNewPanelPopup.selectChartType(ChartType.Line);

        Logger.info("Step 9.Click on Chart Type dropped down menu");
        Logger.info("Step 10.Select Single Bar");
        addNewPanelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check original \"Pie\" - Edit Panel form is displayed");
        assertTrue(addNewPanelPopup.isCorrectChartType(ChartType.Pie),"Check original \"Pie\" - Edit Panel form is NOT displayed");

        Logger.info("Post - Condition");
        addNewPanelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }

    @Test(description = "Verify that \"Check All/Uncheck All\" links are working correctly.")
    public void DA_PANEL_TC64(){
        String pageName = Utility.randomString(5);
        String panelName = Utility.randomString(5);
        String panelName2 = Utility.randomString(5);
        Logger.info("Verify that \"Check All/Uncheck All\" links are working correctly.");

        Logger.info("Step 5. Click 'Add Page' Button");
        homePage.moveMouseToGlobalSetting();
        homePage.clickAddNewPage();

        Logger.info("Step 6. Enter PageName");
        pagePopup.enterPageName(pageName);

        Logger.info("Step 7. Click OK");
        pagePopup.clickOK();

        Logger.info("Step 11.Click 'Choose Panels");
        homePage.clickPanelSetting();

        Logger.info("Step 12. Click 'Create new panel' button");
        choosePanelPopup.clickBtnCreateNewPanel();

        Logger.info("Step 13. Enter a name to Display Name");
        addNewPanelPopup.enterDisplayName(panelName);

        Logger.info("Step 14. click OK button");
        addNewPanelPopup.clickOK();

        Logger.info("Step 15.click cancel button");
        panelConfigurationPopup.clickCancel();

        Logger.info("Step 16. Click 'Create new panel' button");
        choosePanelPopup.clickBtnCreateNewPanel();

        Logger.info("Step 17. Enter a name to Display Name");
        addNewPanelPopup.enterDisplayName(panelName2);

        Logger.info("Step 18. click OK button");
        addNewPanelPopup.clickOK();

        Logger.info("Step 19.click cancel button");
        panelConfigurationPopup.clickCancel();

        Logger.info("Step20. Click 'Administer' link");
        homePage.moveMouseToAdminister();
        homePage.clickAdminister();

        Logger.info("Step21. Click 'Panel' link");
        homePage.moveMouseToAdminister();
        homePage.clickPanels();

        Logger.info("Step22. Click 'Check All' link");
        panelPage.clickCheckAll();

        Logger.info("Step22. Click 'UnCheck All' link");
        panelPage.clickUnCheckAll();



    }



    @AfterMethod
    private void afterMethodPanelTest() {


    }

}
