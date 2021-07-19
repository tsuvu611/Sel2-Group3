package page;

import common.Utility;
import element.*;
import enums.*;

import java.util.List;

public class AddNewPanelPopup extends BasePage {
    private final ComboBox cmbDataProfile = new ComboBox("//select[@id='cbbProfile']");
    private final ComboBox cmbChartType = new ComboBox("//select[@id='cbbChartType']");
    private final ComboBox cmbCategory = new ComboBox("//select[@id='cbbCategoryField']");
    private final ComboBox cmbSeries = new ComboBox("//select[@id='cbbSeriesField']");
    private final TextBox txtDisplayName = new TextBox("//input[@id='txtDisplayName']");
    private final TextBox txtChartTitle = new TextBox("//input[@id='txtChartTitle']");
    private final TextBox txtCaptionCategory = new TextBox("//input[@id='txtCategoryXAxis']");
    private final TextBox txtCaptionSeries = new TextBox("//input[@id='txtValueYAxis']");
    private final CheckBox ckbShowTitle = new CheckBox("//input[@id='chkShowTitle']");
    private final CheckBox ckbSeries = new CheckBox("//input[@id='chkSeriesName']");
    private final CheckBox ckbCategories = new CheckBox("//input[@id='chkCategoriesName']");
    private final CheckBox ckbValue = new CheckBox("//input[@id='chkValue']");
    private final CheckBox ckbPercentage = new CheckBox("//input[@id='chkPercentage']");
    private final Button btnOK = new Button("//input[contains(@onclick,'Dashboard.addPanel(')]");
    private final Button btnCancel = new Button("//input[@onclick= \"Dashboard.closePanelDialog();\"]");

    private final Element elmSettingPanel(String nameSetting) {
        return new Element(String.format("//legend[text()='%s']", nameSetting));
    }

    private final RadioButon radCommon(String id) {
        return new RadioButon(String.format("//input[@id='%s']", id));
    }

    public AddNewPanelPopup enterChartTitle(String panelName) {
        try {
            txtChartTitle.waitForDisplayed();
            txtChartTitle.enter(panelName);
        } catch (Exception e) {
            txtChartTitle.waitForDisplayed();
            txtChartTitle.enter(panelName);
        }
        return this;
    }

    public AddNewPanelPopup enterDisplayName(String panelName) {
        try {
            txtDisplayName.waitForDisplayed();
            txtDisplayName.clear();
            txtDisplayName.enter(panelName);
        } catch (Exception e) {
            txtDisplayName.waitForDisplayed();
            txtDisplayName.clear();
            txtDisplayName.enter(panelName);
        }
        return this;
    }

    public AddNewPanelPopup selectRandomSeries() {
        String[] options = cmbSeries.getText().split("\n");
        int randomNum = Utility.randomNumber(1, options.length - 1);
        cmbSeries.selectByIndex(randomNum);
        return this;
    }

    public AddNewPanelPopup selectCategorySeries() {
        String[] options = cmbCategory.getText().split("\n");
        int randomNum = Utility.randomNumber(1, options.length - 1);
        cmbCategory.selectByIndex(randomNum);
        return this;
    }

    public AddNewPanelPopup selectType(RadioButtonType radioButtonType) {
        radCommon(radioButtonType.getRadioID()).waitForDisplayed();
        radCommon(radioButtonType.getRadioID()).click();
        return this;
    }

    public AddNewPanelPopup selectType(RadioButtonLegends radioButtonLegends) {
        radCommon(radioButtonLegends.getRadioID()).waitForDisplayed();
        radCommon(radioButtonLegends.getRadioID()).click();
        return this;
    }

    public AddNewPanelPopup selectType(RadioButtonStyle radioButtonStyle) {
        radCommon(radioButtonStyle.getRadioID()).waitForDisplayed();
        radCommon(radioButtonStyle.getRadioID()).click();
        return this;
    }

    public AddNewPanelPopup selectChartType(ChartType chartType) {
        cmbChartType.waitForDisplayed();
        cmbChartType.selectByText(chartType.getValue());
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

    public AddNewPanelPopup clickCmbChartType() {
        cmbChartType.waitForDisplayed();
        cmbChartType.click();
        return this;
    }

    public PanelPage clickCancelFromPanel() {
        btnCancel.waitForDisplayed();
        btnCancel.click();
        return new PanelPage();
    }

    public boolean isSettingPanelDisplayAfterDisplayName(RadioButtonType radioButtonType) {
        try {
            elmAfter(txtDisplayName.getXpath(), elmSettingPanel(radioButtonType.getRadioName() + " Settings").getXpath()).waitForDisplayed();
            return elmAfter(txtDisplayName.getXpath(), elmSettingPanel(radioButtonType.getRadioName() + " Settings").getXpath()).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChartTypeList(List<String> chartTypeList) {
        return cmbChartType.getListData().equals(chartTypeList);
    }


    public boolean isCmbDataProfileAlphaOrder() {
        return cmbDataProfile.isCmbAlphabetOrder();
    }

    public boolean isDataProfileContain(String panelName) {
        return cmbDataProfile.getText().contains(panelName);
    }

    public boolean isCategoryEnable() {
        Logger.info("Is Category Enable");
        return cmbCategory.isEnable();
    }

    public boolean isCaptionCategoryEnable() {
        Logger.info("Is Caption Category Enable");
        return txtCaptionCategory.isEnable();
    }

    public boolean isCaptionSeriesEnable() {
        Logger.info("Is Caption Series Enable");
        return txtCaptionSeries.isEnable();
    }

    public AddNewPanelPopup enterTxtCaptionCategory(String caption){
        txtCaptionCategory.waitForDisplayed();
        txtCaptionCategory.enter(caption);
        return this;
    }

    public AddNewPanelPopup enterTxtCaptionSeries(String caption){
        txtCaptionSeries.waitForDisplayed();
        txtCaptionSeries.enter(caption);
        return this;
    }

    public boolean isCorrectCaptionCategory(String caption){
        txtCaptionCategory.waitForDisplayed();
        return txtCaptionCategory.getText().equals(caption);
    }

    public boolean isCorrectCaptionSeries(String caption){
        txtCaptionSeries.waitForDisplayed();
        return txtCaptionSeries.getText().equals(caption);
    }

    public boolean isCorrectChartType(ChartType chartType){
        cmbChartType.waitForDisplayed();
        return cmbChartType.getSelectedText().equals(chartType.getValue());
    }

    public boolean isSeriesEnable() {
        Logger.info("Is Series Enable");
        return cmbSeries.isEnable();
    }

    public boolean isCKBCategoryEnable(){
        return ckbCategories.isEnable();
    }
    public boolean isCKBSeriesEnable(){
        return ckbSeries.isEnable();
    }
    public boolean isCKBValueEnable(){
        return ckbValue.isEnable();
    }
    public boolean isCKBPercentageEnable(){
        return ckbValue.isEnable();
    }

    public boolean isCategoryAndCaptionDisableSeriesEnable() {
        return !isCategoryEnable() && !isCaptionCategoryEnable() && !isCaptionSeriesEnable() && isSeriesEnable();
    }

    public boolean isCategoryDisableSeriesAndCaptionEnable() {
        return !isCategoryEnable() && isCaptionCategoryEnable() && isCaptionSeriesEnable() && isSeriesEnable();
    }

    public boolean isCategoryAndSeriesAndCaptionEnable() {
        return isCategoryEnable() && isCaptionCategoryEnable() && isCaptionSeriesEnable() && isSeriesEnable();
    }

    public AddNewPanelPopup selectDataProfile(DataProfile dataProfile) {
        cmbDataProfile.waitForDisplayed();
        cmbDataProfile.selectByText(dataProfile.getValue());
        return this;
    }

    public AddNewPanelPopup setStateCkbShowTitle(CheckBoxState checkBoxState) {
        ckbShowTitle.waitForDisplayed();
        ckbShowTitle.setCheckBoxState(checkBoxState);
        return this;
    }
    public AddNewPanelPopup setStateCkbSeries(CheckBoxState checkBoxState) {
        ckbSeries.waitForDisplayed();
        ckbSeries.setCheckBoxState(checkBoxState);
        return this;
    }
    public AddNewPanelPopup setStateCkbCategories(CheckBoxState checkBoxState) {
        ckbCategories.waitForDisplayed();
        ckbCategories.setCheckBoxState(checkBoxState);
        return this;
    }
    public AddNewPanelPopup setStateCkbValue(CheckBoxState checkBoxState) {
        ckbValue.waitForDisplayed();
        ckbValue.setCheckBoxState(checkBoxState);
        return this;
    }
    public AddNewPanelPopup setStateCkbPercentage(CheckBoxState checkBoxState) {
        ckbPercentage.waitForDisplayed();
        ckbPercentage.setCheckBoxState(checkBoxState);
        return this;
    }

    public boolean isAddnewPanelPopUpDisplayed(){
        return btnOK.isDisplayed();
    }

    public boolean isChartTypeAndDataProfileAndDisplayNameAndChartTitleAndShowTitleAndLegendsChanged(ChartType chartType, DataProfile dataProfile, String displayName, String chartTitle, CheckBoxState checkBoxState, RadioButtonLegends radioButtonLegends) {
        if (checkBoxState.getState().equals("on")) {
            if (!ckbShowTitle.isSelected()) return false;
        } else if (checkBoxState.getState().equals("off")) {
            if (ckbShowTitle.isSelected()) return false;
        }
        return (cmbChartType.getSelectedText().equals(chartType.getValue()) &&
                txtChartTitle.getText().equals(chartTitle) &&
                cmbDataProfile.getSelectedText().equals(dataProfile) &&
                txtDisplayName.getText().equals(displayName) &&
                radCommon(radioButtonLegends.getRadioID()).isSelected());
    }

    public boolean areAllSettingChanged(){

        return false;
    }


}
