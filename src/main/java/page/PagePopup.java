package page;

import element.*;
import enums.CheckBoxState;

public class PagePopup {
    private final TextBox txtNewPageName = new TextBox("//input[@id=\"name\"]");
    private final ComboBox cmbDisplayAfter = new ComboBox("//select[@id='afterpage']");
    private final Button btnOK = new Button("//input[@id=\"OK\"]");
    private final Button btnCancel = new Button("//input[@id=\"Cancel\"]");
    private final CheckBox ckPublic = new CheckBox("//input[@id='ispublic']");
    private final ComboBox cmbParentPage = new ComboBox("//select[@id='parent']");
    private final ComboBox cmbColumnNumber = new ComboBox("//select[@id='columnnumber']");
    private final Label lblTitle = new Label("//div[@id='div_popup']//h2[text()='Edit Page']");

    public PagePopup enterPageName(String pageName){
        this.txtNewPageName.enter(pageName);
        return new PagePopup();
    }

    public PagePopup enterRenamePageName(String pageName){
        this.txtNewPageName.clear();
        this.txtNewPageName.enter(pageName);
        return new PagePopup();
    }

    public HomePage clickOK(){
        this.btnOK.click();
        return new HomePage();
    }

    public HomePage clickCancel(){
        this.btnCancel.click();
        return new HomePage();
    }

    public PagePopup setPublicCheckBox(CheckBoxState checkBoxState){
        ckPublic.setCheckBoxState(checkBoxState);
        return this;
    }

    public PagePopup selectDisplayAfter(String pageName){
        cmbDisplayAfter.selectByText(pageName);
        return this;
    }

    public PagePopup clickDisplayAfter(){
        cmbDisplayAfter.click();
        return this;
    }

    public PagePopup clickParentPage(){
        cmbParentPage.click();
        return this;
    }

    public PagePopup selectParentPage(String parentPage){
        cmbParentPage.selectByText(parentPage);
        return this;
    }

    public PagePopup clickColumnNumber(){
        cmbColumnNumber.click();
        return this;
    }

    public PagePopup selectColumnNumber(String columnNumber){
        cmbColumnNumber.selectByText(columnNumber);
        return this;
    }

    public boolean verifyEditPopupIsDisplayed(){
        return lblTitle.isDisplayed();
    }
}
