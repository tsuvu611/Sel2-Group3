package enums;

public enum RadioButtonStyle {
    Style2D("rdoChartStyle2D"),
    Style3D("rdoChartStyle3D");

    private final String name;

    RadioButtonStyle(String value) {
        this.name = value;
    }

    public String getRadioID(){
        return name;
    }

}
