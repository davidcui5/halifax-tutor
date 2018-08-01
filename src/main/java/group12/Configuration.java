package group12;

import group12.data_access.IDataAccessObject;

public class Configuration {
    static IDataAccessObject db;

    public static void setDb(IDataAccessObject dbd) {
        db = dbd;
    }

    private static Configuration instance = new Configuration();
    private boolean isSearchAuth;

    public boolean isSearchAuth() {
        this.instance.isSearchAuth = db.getSearchAuthConf();
        return isSearchAuth;
    }

    public static Configuration getInstance() {
        return instance;
    }

    private Configuration() {
    }
}