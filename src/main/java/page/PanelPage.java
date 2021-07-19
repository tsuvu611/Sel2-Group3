package page;

import element.Button;
import element.Label;

public class PanelPage extends BasePage {
    private final Button btnCheckAll = new Button("//a[text()='Check All']");
    private final Button btnUnCheckAll = new Button("//a[text()='UnCheck All']");
    private final Button btnDeleteAll = new Button("//a[@href='javascript:Dashboard.deletePanels();']");
    private final Button btnAddNew = new Button("//a[text()='Add New']");

    private final Label lblPanelName(String itemName) {
        return new Label(String.format("//a[text()='%s']", itemName));
    }

    private final Button btnEdit(String panelName) {
        return new Button(String.format("//a[text()='%s']//parent::td//following-sibling::td//a[text()=\"Edit\"]", panelName));
    }

    public PanelPage clickCheckAll() {
        btnCheckAll.waitForDisplayed();
        btnCheckAll.click();
        return this;
    }

    public PanelPage clickUnCheckAll() {
        btnUnCheckAll.waitForDisplayed();
        btnUnCheckAll.click();
        return this;
    }

    public AddNewPanelPopup clickAddNew() {
        try {
            btnAddNew.waitForDisplayed();
            btnAddNew.click();
        } catch (Exception e) {
            clickAdminister();
            clickPanels();
            btnAddNew.click();
        }
        return new AddNewPanelPopup();
    }

    public PanelPage clickDeleteAll() {
        btnDeleteAll.waitForDisplayed();
        btnDeleteAll.click();
        return this;
    }

    public AddNewPanelPopup clickPanelEdit(String panelname) {
        btnEdit(panelname).waitForDisplayed();
        btnEdit(panelname).click();
        return new AddNewPanelPopup();
    }

    public boolean isPanelNameDisplayed(String panelName) {
        lblPanelName(panelName).waitForDisplayed();
        return lblPanelName(panelName).isDisplayed();
    }


}
