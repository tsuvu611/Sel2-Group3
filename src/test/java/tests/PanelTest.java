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
//        panelPopup.selectRandomSeries();

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
//        panelPopup.selectRandomSeries();
        panelPage = panelPopup.clickOKFromPanel();

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
        panelPopup = panelPage.clickAddNew();

        Logger.info("VP: Verify that chart panel setting form is displayed with corresponding panel type selected");
        assertTrue(panelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Chart), "Chart setting is not displayed after Display Name as expected.");

        Logger.info("Step 6. Select Indicator type");
        panelPopup.selectType(RadioButtonType.Indicator);

        Logger.info("VP: Verify that indicator panel setting form is displayed with corresponding panel type selected");
        assertTrue(panelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Indicator), "Chart settings is not displayed after Display Name as expected.");
        Logger.info("Step 6. Select Indicator type");

        Logger.info("Step 8. Select Report type");
        panelPopup.selectType(RadioButtonType.Report);

        Logger.info("VP: Verify that indicator panel setting form is displayed with corresponding panel type selected");
        Logger.info("Report panel setting form is no longer available.\n" +
                "-> Remove this check");
        assertTrue(panelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.Report), "Indicator settings is not displayed after Display Name as expected.");

        Logger.info("Step 10. Select Heat Maps type");
        panelPopup.selectType(RadioButtonType.HeatMap);

        Logger.info("VP: Verify that heatmap panel setting form is displayed with corresponding panel type selected");
        assertTrue(panelPopup.isSettingPanelDisplayAfterDisplayName(RadioButtonType.HeatMap), "Heat Map settings is not displayed after Display Name as expected.");

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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 5. Enter display name to \"Display name\" field.");
        panelPopup.enterDisplayName(displayName);
//        panelPopup.selectRandomSeries();

        Logger.info("Step 6. Click on OK button");
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("Step 7. Click on Add new link again.");
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 8. Enter display name same with previous display name to \"display name\" field. ");
        panelPopup.enterDisplayName(displayName);
        panelPopup.selectRandomSeries();

        Logger.info("Step 9. Click on OK button");
        panelPopup.clickOKFromPanel();

        Logger.info("VP: Warning message: \"Duplicated panel already exists. Please enter a different name.\" show up");
        assertTrue(panelPopup.isAlertMessageCorrect(errorMessage), "The alert Message is not displayed as expected.");

        Logger.info("Post - Condition");
        panelPopup.clickOKAlert();
        panelPage = panelPopup.clickCancelFromPanel();
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
        panelPopup = panelPage.clickAddNew();

        Logger.info("VP. Verify that Data Profile list is in alphabetical order");
        assertTrue(panelPopup.isCmbDataProfileAlphaOrder(), "The Data Profile list is not in alphabetical order as expected.");

        Logger.info("Step 6. Enter a display name to display name field");
        panelPopup.enterDisplayName(displayName);
//        panelPopup.selectRandomSeries();

        Logger.info("Step 7. Click on OK button");
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("Step 8. Click on Edit link");
        panelPage.clickPanelEdit(displayName);

        Logger.info("VP. Verify that Data Profile list is in alphabetical order");
        assertTrue(panelPopup.isCmbDataProfileAlphaOrder(), "The Data Profile list is not in alphabetical order as expected.");

        Logger.info("Post - Condition");
        panelPage = panelPopup.clickCancelFromPanel();
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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 5. Enter name to Name textbox");
        panelPopup.enterDisplayName(displayName1);
        panelPopup.selectRandomSeries();

        Logger.info("Step 6. Click on OK button");
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("Step 7. Click on Administer/Panels link");
        panelPage.clickAdminister();
        panelPage.clickPanels();

        Logger.info("Step 8. Click on Add new link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("VP. Verify that \"" + displayName1 + "\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        assertTrue(panelPopup.isDataProfileContain(displayName1), displayName1 + "\" data profiles are NOT populated correctly under the \"Data Profile\" dropped down menu as expected.");

        Logger.info("Step 10. Enter name to Name textbox");
        panelPopup.enterDisplayName(displayName2);
        panelPopup.selectRandomSeries();

        Logger.info("Step 11. Click on OK button");
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("Step 12. Click on Edit link");
        panelPage.clickPanelEdit(displayName2);

        Logger.info("VP. Verify that \"" + displayName1 + "\" data profiles are populated correctly under the \"Data Profile\" dropped down menu.");
        assertTrue(panelPopup.isDataProfileContain(displayName1), displayName1 + "\" data profiles are NOT populated correctly under the \"Data Profile\" dropped down menu as expected.");
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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Enter value into Display Name field");
        panelPopup.enterDisplayName(displayName);
        panelPopup.selectRandomSeries();

        Logger.info("Step . Enter value into Chart Title field with special characters except \"@\"");
        panelPopup.enterChartTitle(chartTitleName1);

        Logger.info("Step 7. Click on OK button");
        panelPopup.clickOKFromPanel();

        Logger.info("VP: Message \"Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%;\" is displayed");
        assertTrue(panelPopup.isAlertMessageCorrect(errorMsg), "Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\\\"#[]{}=%; is not displayed as expected.");

        Logger.info("Step 9. Close Warning Message box");
        panelPopup.closeAlert();
        panelPage = panelPopup.clickCancelFromPanel();

        Logger.info("Step 10. Click Add New link");
        panelPage.clickAddNewPage();

        Logger.info("Step 11. Enter value into Display Name field");
        panelPopup.enterDisplayName(displayName1);
        panelPopup.selectRandomSeries();

        Logger.info("Step 12. Enter value into Chart Title field with special characters except \"@\"");
        panelPopup.enterChartTitle(chartTitleName2);

        Logger.info("Step 7. Click on OK button");
        panelPage = panelPopup.clickOKFromPanel();

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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("VP: Check that 'Chart Type' are listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line'");
        assertTrue(panelPopup.isChartTypeList(list), "'Chart Type' are NOT listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line' as expected.");

        Logger.info("Post - Condition");
        panelPage = panelPopup.clickCancelFromPanel();
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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 11. Select 'Pie' Chart Type");
        panelPopup.selectChartType(ChartType.Pie);

        Logger.info("VP: Check that 'Category' and 'Caption' are disabled, 'Series' is enabled");
        assertTrue(panelPopup.isCategoryAndCaptionDisableSeriesEnable(), "'Category' and 'Caption' are NOT disabled, 'Series' is NOT enabled as expected.");

        Logger.info("Step 13. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 14. Select 'Single Bar' Chart Type");
        panelPopup.selectChartType(ChartType.SingleBar);

        Logger.info("VP: Check that 'Category' is disabled, 'Series' and 'Caption' are enabled");
        assertTrue(panelPopup.isCategoryDisableSeriesAndCaptionEnable(), "'Category' is NOT disabled, 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 16. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 17. Select 'Stacked Bar' Chart Type");
        panelPopup.selectChartType(ChartType.StackedBar);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(panelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 19. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 20. Select 'Group Bar' Chart Type");
        panelPopup.selectChartType(ChartType.GroupBar);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(panelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Step 21. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 22. Select 'Line' Chart Type");
        panelPopup.selectChartType(ChartType.Line);

        Logger.info("VP: Check that 'Category' ,'Series' and 'Caption' are enabled");
        assertTrue(panelPopup.isCategoryAndSeriesAndCaptionEnable(), "'Category', 'Series' and 'Caption' are NOT enabled as expected.");

        Logger.info("Post - Condition");
        panelPage = panelPopup.clickCancelFromPanel();
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
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 10. Click 'Chart Type' drop-down menu");
        panelPopup.clickCmbChartType();

        Logger.info("Step 11. Select 'Pie' Chart Type");
        panelPopup.selectChartType(chartType);

        Logger.info("Step 12. Select 'Data Profile' drop-down menu");
        panelPopup.selectDataProfile(dataProfile);

        Logger.info("Step 13. Enter 'Display Name' and 'Chart Title'");
        panelPopup.enterDisplayName(displayName);
        panelPopup.enterChartTitle(chartTitle);

        Logger.info("Step 14. Select 'Show Title' checkbox");
        panelPopup.setStateCkbShowTitle(checkBoxState);

        Logger.info("Step 15. Select 'Legends' radio button");
        panelPopup.selectType(radioButtonLegends);

        Logger.info("Step 16. Select 'Style' radio button");
        panelPopup.selectType(radioButtonStyle1);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(panelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step . Select 'Style' radio button");
        panelPopup.selectType(radioButtonStyle2);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(panelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step 18. Click OK button");
//        panelPopup.selectRandomSeries();
//        panelPopup.selectCategorySeries();
        panelPopup.clickOKFromPanel();

        Logger.info("Select a page in drop-down menu");
//        panelPopup.clickPage(pageName);

        Logger.info("Step 22. Click 'Edit Panel' button of panel '" + displayName + "'");
        panelPopup = panelPage.clickPanelEdit(displayName);

        Logger.info("Step 23. Select 'Style' radio button");
        panelPopup.selectType(radioButtonStyle1);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(panelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Step 25. Select 'Style' radio button");
        panelPopup.selectType(radioButtonStyle2);

        Logger.info("VP: Check that settings of 'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' stay unchanged.");
        assertFalse(panelPopup.isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(chartType, dataProfile, displayName, chartTitle, checkBoxState, radioButtonLegends), "'Chart Type', 'Data Profile', 'Display Name', 'Chart Title', 'Show Title' and 'Legends' DON'T stay unchanged.");

        Logger.info("Post - Condition");
        panelPage = panelPopup.clickCancelFromPanel();
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
        String pageName = "";
        String displayName = "";
        String chartTitle = "";
        ChartType chartType = ChartType.Pie;
        DataProfile dataProfile = DataProfile.TestCaseExecution;
        CheckBoxState checkBoxState = CheckBoxState.ON;
        RadioButtonLegends radioButtonLegends = RadioButtonLegends.Top;
        RadioButtonStyle radioButtonStyle1 = RadioButtonStyle.Style3D;
        RadioButtonStyle radioButtonStyle2 = RadioButtonStyle.Style2D;
        Logger.info("DA_MP_TC39 - Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons");

        Logger.info("Step 3. Click Administer link");
        homePage.clickAdminister();

        Logger.info("Step 4. Click Panel link");
        panelPage = homePage.clickPanels();

        Logger.info("Step 5. Click Add New link");
        panelPopup = panelPage.clickAddNew();

        Logger.info("Step 6. Click None radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 8. Click top radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 10. Click Right radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 12. Click Bot radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 14. Click Left radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 16. Create a new panel");
        panelPopup.enterDisplayName(displayName);
        panelPopup.selectRandomSeries();
        panelPage = panelPopup.clickOKFromPanel();

        Logger.info("Step 17. Click Edit Panel link");
        panelPopup = panelPage.clickPanelEdit(displayName);

        Logger.info("Step 18. Click None radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.None);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 20. Click top radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Top);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 22. Click Right radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Right);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 24. Click Bot radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Bottom);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Step 26. Click Left radio button for Legend");
        panelPopup.selectType(RadioButtonLegends.Left);

        Logger.info("VP: All settings are un change in Add New Panel dialog");
        assertFalse(panelPopup.areAllSettingChanged(), "All settings are change as not expected");

        Logger.info("Post - Condition");
        panelPage = panelPopup.clickCancelFromPanel();
        panelPage.clickCheckAll();
        panelPage.clickDeleteAll();
        panelPage.clickOKAlert();
    }


    @AfterMethod
    private void afterMethodPanelTest() {


    }

}
