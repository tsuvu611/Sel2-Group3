package page;

import element.Button;

public class PanelConfigurationPopup {
    private final Button btnOK = new Button("//input[contains(@onclick,'Dashboard.addPanelToPage(')]");
    private final Button btnCancel = new Button("//input[contains(@onclick,'Dashboard.closePanelConfigurationDlg();')]");

    public HomePage clickOK(){
        btnOK.waitForDisplayed();
        btnOK.click();
        return new HomePage();
    }

}
