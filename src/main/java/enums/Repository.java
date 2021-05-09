package enums;

public enum Repository {
    SAMPLE("TestRepository"), TEST("TestRepository");

    private final String repoName;

    Repository(String repoName) {
        this.repoName = repoName;
    }

    public String getName() {
        return repoName;
    }
}
