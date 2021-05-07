package data;

import common.Constant;

public class Repository {
    private String repository;

    public Repository() {

        this.repository = Constant.REPOSITORY;
    }

    public String getRepository() {
        return repository;
    }

    public Repository(String repository){
        this.repository = repository;

    }
}

