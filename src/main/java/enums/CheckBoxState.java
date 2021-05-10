package enums;

public enum CheckBoxState {
    ON("on"), OFF("off");

    private final String state;

    CheckBoxState(String value) {
        this.state = value;
    }

    public String getState(){
        return state;
    }

}
