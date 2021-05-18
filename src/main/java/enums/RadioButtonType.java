package enums;

public enum RadioButtonType {
    Chart("radPanelType0"),
    Indicator("radPanelType1"),
    Report("radPanelType2"),
    HeatMap("radPanelType3");

    private final String name;

    RadioButtonType(String value) {
        this.name = value;
    }

    public String getRadioID(){
        return name;
    }

}
