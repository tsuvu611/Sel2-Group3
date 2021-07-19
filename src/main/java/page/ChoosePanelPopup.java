package page;

import element.Button;
import element.Element;
import element.ListElement;

import java.util.List;

public class ChoosePanelPopup extends BasePage{
    private final ListElement listElement = new ListElement("//a[contains(@onclick,\"Dashboard.showPanelConfiguration(\")]");
    private final Element elmAction = new Element("(//*[contains(text(),'Action')])[1]");
    private final Element elmImplementationByPriority = new Element("(//*[contains(text(),'Priority')])[1]");
    private final Element elmImplementationByStatus = new Element("(//*[contains(text(),'Priority')])[1]");
    private final Button btnCreateNewPanel = new Button("//span[contains(text(),'Create new panel')]");
    private final Element elmNewlyPanelLink = new Element("(//*[contains(@title,'Click to add panel')])[1]");

    public boolean arePanelsPopulatedAndSortedCorrectly(List<String> list) {
        return listElement.getElementsText().equals(list);
    }

    public AddNewPanelPopup clickBtnCreateNewPanel(){
        btnCreateNewPanel.waitForDisplayed();
        btnCreateNewPanel.click();
        return new AddNewPanelPopup();
    }

    public PanelConfigurationPopup clickAction(){
        elmAction.waitForDisplayed();
        elmAction.click();
        return new PanelConfigurationPopup();
    }

    public PanelConfigurationPopup clickElmImplementByPriority(){
        elmImplementationByPriority.waitForDisplayed();
        elmImplementationByPriority.click();
        return new PanelConfigurationPopup();
    }

    public PanelConfigurationPopup clickElmImplementationByStatus(){
        elmImplementationByStatus.waitForDisplayed();
        elmImplementationByStatus.click();
        return new PanelConfigurationPopup();
    }

    public PanelConfigurationPopup clickNewlyPanelLink(){
        elmNewlyPanelLink.waitForDisplayed();
        elmNewlyPanelLink.click();
        return new PanelConfigurationPopup();
    }

}
