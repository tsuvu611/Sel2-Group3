package page;

import common.Utility;
import element.*;

public class PanelPopup extends BasePage{
    private final ComboBox cmbColumnNumber = new ComboBox("//select[@id='cbbProfile']");
    private final ComboBox cmbChartType = new ComboBox("//select[@id='cbbChartType']");
    private final ComboBox cmbCategory = new ComboBox("//select[@id='cbbCategoryField']");
    private final ComboBox cmbSeries = new ComboBox("//select[@id='cbbSeriesField']");
    private final TextBox txtDisplayName = new TextBox("//input[@id='txtDisplayName']");
    private final TextBox txtChartTitle = new TextBox("//input[@id='txtChartTitle']");
    private final CheckBox ckbShowTitle = new CheckBox("//input[@id='chkShowTitle']");
    private final CheckBox ckbSeries = new CheckBox("//input[@id='chkSeriesName']");
    private final CheckBox ckbCategories = new CheckBox("//input[@id='chkCategoriesName']");
    private final CheckBox ckbValue = new CheckBox("//input[@id='chkValue']");
    private final CheckBox ckbPercentage = new CheckBox("//input[@id='chkPercentage']");
    private final Button btnOK = new Button("//input[contains(@onclick,'Dashboard.addPanel(')]");
    private final Button btnCancel = new Button("//input[@onclick= \"Dashboard.closePanelDialog();\"]");
    private final Element elmChartPanelSetting = new Element("//fieldset[@id='fdSettings']");

    public PanelPopup enterDisplayName(String panelName) {
        try {
            txtDisplayName.waitForDisplayed();
            txtDisplayName.enter(panelName);
        } catch (Exception e) {
            txtDisplayName.waitForDisplayed();
            txtDisplayName.enter(panelName);
        }
        return this;
    }

    public PanelPopup selectRandomSeries() {
        String[] options = cmbSeries.getText().split("\n");
        int randomNum = Utility.randomNumber(1, options.length - 1);
        cmbSeries.selectByIndex(randomNum);
        return this;
    }

    public PanelConfigurationPopup clickOK() {
        btnOK.waitForDisplayed();
        btnOK.click();
        return new PanelConfigurationPopup();
    }

    public PanelPage clickOKFromPanel() {
        btnOK.waitForDisplayed();
        btnOK.click();
        return new PanelPage();
    }

    public PanelPage clickCancelFromPanel() {
        btnCancel.waitForDisplayed();
        btnCancel.click();
        return new PanelPage();
    }

}
