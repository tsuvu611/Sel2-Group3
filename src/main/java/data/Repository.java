package data;

import common.Constant;

public class Repository {
    private String repoName;

    public Repository() {
        this.repoName = enums.Repository.SAMPLE.getName();
    }

    public String getRepoName() {
        return repoName;
    }

    public Repository(String repoName){
        this.repoName = repoName;
    }

    public Repository(enums.Repository repository) {
        this.repoName = repository.getName();
    }
}

