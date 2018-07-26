package group12;

public class Configuration {
    private Configuration instance;
    private boolean isSearchAuth;

    public boolean isSearchAuth() {
        return isSearchAuth;
    }

    public Configuration getInstance() {
        return instance;
    }

    private Configuration() {
        this.instance = new Configuration();
//        this.instance.isSearchAuth=
    }
}