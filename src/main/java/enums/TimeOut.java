package enums;

public enum TimeOut {
    SHORT_TIMEOUT(5), LONG_TIMEOUT(60), TIMEOUT(30);

    private final int value;

    TimeOut(int value) {
        this.value = value;
    }

    public int getTimeout() {
        return value;
    }
}
