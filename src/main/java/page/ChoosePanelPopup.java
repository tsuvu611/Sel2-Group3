package page;

import element.ListElement;

import java.util.List;

public class ChoosePanelPopup extends BasePage{
    private final ListElement listElement = new ListElement("//a[contains(@onclick,\"Dashboard.showPanelConfiguration(\")]");

    public boolean arePanelsPopulatedAndSortedCorrectly(List<String> list) {
        System.out.println(list.size() + " " + listElement.getElementsText().size());
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(listElement.getElementsText().get(i))) {
                System.out.println(i);
                System.out.println(list.get(i));
                System.out.println(listElement.getElementsText().get(i));
            }
        }
        return listElement.getElementsText().equals(list);
    }

}
