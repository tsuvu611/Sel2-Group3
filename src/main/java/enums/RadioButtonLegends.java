package enums;

public enum RadioButtonLegends {
    None("radPlacementNone"),
    Top("radPlacementTop"),
    Right("radPlacementRight"),
    Bottom("radPlacementBottom"),
    Left("radPlacementLeft");

    private final String name;

    RadioButtonLegends(String value) {
        this.name = value;
    }

    public String getRadioID() {
        return name;
    }

}
