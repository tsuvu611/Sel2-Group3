package enums;

public enum RadioButtonType {
    Chart("radPanelType0","Chart"),
    Indicator("radPanelType1","Indicator"),
    Report("radPanelType2","Report"),
    HeatMap("radPanelType3","Heat Map");

    private final String id;
    private final String name;

    RadioButtonType(String id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getRadioID(){
        return id;
    }

    public String getRadioName(){
        return name;
    }



}
