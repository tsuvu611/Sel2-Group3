package enums;

public enum ChartType {
    Pie("Pie"),
    SingleBar("Single Bar"),
    Line("Line"),
    StackedBar("Stacked Bar"),
    GroupBar("Group Bar");

    private final String value;

    ChartType(){
        this.value = "Pie";
    }

    ChartType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
