package page;

import element.ListElement;

import java.util.List;

public class ChoosePanelPopup extends BasePage{
    private final ListElement listElement = new ListElement("//a[contains(@onclick,\"Dashboard.showPanelConfiguration(\")]");

    public boolean arePanelsPopulatedAndSortedCorrectly(List<String> list) {
        return listElement.getElementsText().equals(list);
    }

}
