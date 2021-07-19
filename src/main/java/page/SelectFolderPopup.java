package page;

import element.*;
import enums.CheckBoxState;

public class SelectFolderPopup extends BasePage{
    private final Element elmCarRental = new Element("//*[@id=\"async_html_2\"]/table/tbody/tr/td/table/tbody/tr/td/div/table[1]/tbody/tr/td[2]/a[2]");
    private final Element elmCArRentalAction = new Element("//*[@id=\"async_html_2\"]/table/tbody/tr/td/table/tbody/tr/td/div/table[1]/tbody/tr/td[2]/div/table/tbody/tr/td[2]/a[2]");
    private final Element elmCarRentalActionCar = new Element("//*[@id=\"async_html_2\"]/table/tbody/tr/td/table/tbody/tr/td/div/table[1]/tbody/tr/td[2]/div/table/tbody/tr/td[2]/div/table[1]/tbody/tr/td[2]/a[2]");
    private final Button btnOK = new Button("//*[@id='btnFolderSelectionOK']");

    public SelectFolderPopup selectRandomFolder(){
        elmCarRental.waitForDisplayed();
        elmCarRental.click();
        elmCArRentalAction.waitForDisplayed();
        elmCArRentalAction.click();
        elmCarRentalActionCar.waitForDisplayed();
        elmCarRentalActionCar.click();
        return this;
    }

    public PanelConfigurationPopup clickOK(){
        btnOK.waitForDisplayed();
        btnOK.click();
        return  new PanelConfigurationPopup();
    }

    public boolean isSelectFolderDisplayed(){
        return btnOK.isDisplayed();
    }


}
