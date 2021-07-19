package page;

import element.Button;
import element.ComboBox;
import element.TextBox;
import org.w3c.dom.Text;

import java.util.List;

public class PanelConfigurationPopup extends BasePage {
    private final Button btnOK = new Button("//input[contains(@onclick,'Dashboard.addPanelToPage(')]");
    private final Button btnCancel = new Button("//input[contains(@onclick,'Dashboard.closePanelConfigurationDlg();')]");
    private final ComboBox CmbSelectPage = new ComboBox("//*[@id='cbbPages']");
    private final TextBox txtHeight = new TextBox("//*[@id='txtHeight']");
    private final TextBox txtFolder = new TextBox("//*[@id='txtFolder']");
    private final Button BtnFolder = new Button("//*[@class='panel_setting_treefolder']");
    public HomePage clickOK(){
        btnOK.waitForDisplayed();
        btnOK.click();
        return new HomePage();
    }

    public boolean isPanelConfigDisplayed(){
        return btnOK.isDisplayed();
    }
    public HomePage clickCancel(){
        btnCancel.waitForDisplayed();
        btnCancel.click();
        return new HomePage();
    }

    public SelectFolderPopup clickBtnFolder(){
        BtnFolder.waitForDisplayed();
        BtnFolder.click();
        return new SelectFolderPopup();
    }

    public PanelConfigurationPopup clickCmbSelectPage(){
        CmbSelectPage.waitForDisplayed();
        CmbSelectPage.click();
        return this;
    }

    public boolean isCmbSelectContain(String pageName){
        CmbSelectPage.waitForDisplayed();
        try{
                CmbSelectPage.selectByText(pageName);
                return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public PanelConfigurationPopup enterHeight(String height){
        txtHeight.waitForDisplayed();
        txtHeight.enter(height);
        return this;
    }

    public PanelConfigurationPopup enterTxtFolder(String height){
        txtFolder.waitForDisplayed();
        txtFolder.enter(height);
        return this;
    }

    public boolean isCorrectFolderPath(){
        txtFolder.waitForDisplayed();
        return true;
    }

}
