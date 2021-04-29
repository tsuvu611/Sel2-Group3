package page;

import element.Label;

public class HomePage {
    private final Label lblUsername = new Label("//div[@id-'test']");
    private final Label lblRepoName = new Label("xpath will be updated later");
    private final Label lblTabName = new Label("//a[@class='active']");

    public String getTabName(){
        return this.lblTabName.getText();
    }


}
