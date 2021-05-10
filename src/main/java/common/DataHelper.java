package common;

import data.Repository;
import data.User;

public class DataHelper {

    public static User getRandomUser() {
        return new User(Utility.randomString(8), Utility.randomString(8));
    }

    public static Repository getRandomRepo() {
        return new Repository(Utility.randomString(8));
    }

    public static Repository getTestRepo() {
        return new Repository(enums.Repository.TEST);
    }
}
