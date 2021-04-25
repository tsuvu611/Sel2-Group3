package enums;

public enum DriverType {
    CHROME("Chrome"), FIREFOX("Firefox");

    private final String value;

    DriverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
