package page;

import element.Button;
import element.Label;

public class PanelPage extends BasePage{
    private final Button btnCheckAll = new Button("//a[text()='Check All']");
    private final Button btnDeleteAll = new Button("//a[@href='javascript:Dashboard.deletePanels();']");
    private final Button btnAddNew = new Button("//a[text()='Add New']");
    private final Label lblPanelName(String itemName) {
        return new Label(String.format("//a[text()='%s']", itemName));
    }


    public PanelPage clickCheckAll() {
        btnCheckAll.waitForDisplayed();
        btnCheckAll.click();
        return this;
    }

    public PanelPopup clickAddNew() {
        btnAddNew.waitForDisplayed();
        btnAddNew.click();
        return new PanelPopup();
    }

    public PanelPage clickDeleteAll() {
        btnDeleteAll.waitForDisplayed();
        btnDeleteAll.click();
        return this;
    }

    public boolean isPanelNameDisplayed(String panelName){
        lblPanelName(panelName).waitForDisplayed();
        return lblPanelName(panelName).isDisplayed();
    }



}
