package page;

import element.*;

public class PagePopup {
    private final TextBox txtNewPageName = new TextBox("//input[@id=\"name\"]");
    private final ComboBox cmbDisplayAfter = new ComboBox("//select[@id='afterpage']");
    private final Button btnOK = new Button("//input[@id=\"OK\"]");
    private final CheckBox ckPublic = new CheckBox("//input[@id='ispublic']");
    private final ComboBox cmbParentPage = new ComboBox("//select[@id='parent']");
    private final Label lblTitle = new Label("//div[@id='div_popup']//h2[text()='Edit Page']");

    public PagePopup enterPageName(String pageName){
        this.txtNewPageName.enter(pageName);
        return new PagePopup();
    }

    public HomePage clickOK(){
        this.btnOK.click();
        return new HomePage();
    }

    public PagePopup checkThePubblicCheckBox(){
        ckPublic.checkTheCheckBox();
        return this;
    }
    public PagePopup uncheckThePubblicCheckBox(){
        ckPublic.uncheckTheCheckBox();
        return this;
    }

    public PagePopup selectDisplayAfter(String Item){
        cmbDisplayAfter.selectByText(Item);
        return this;
    }

    public PagePopup clickDisplayAfter(){
        cmbDisplayAfter.click();
        return this;
    }

    public PagePopup clickCmbParentPage(){
        cmbParentPage.click();
        return this;
    }

    public PagePopup selectParentPage(String parentPage){
        cmbParentPage.selectByText(parentPage);
        return this;
    }

    public boolean verifyEditPopupIsDisplayed(){
        return lblTitle.isDisplayed();
    }
}
