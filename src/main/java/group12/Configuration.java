package group12;

public class Configuration {
    private Configuration instance;
    private boolean hasDigit;
    private boolean hasLowerCase;
    private boolean hasUpperCase;
    private boolean hasSpecial;
    private boolean hasWhiteSpace;
    private int numChars;

    public Configuration getInstance() {
        return instance;
    }

    protected Configuration() {
        this.instance = new Configuration();
    }

    public boolean isHasDigit() {
        return hasDigit;
    }

    public boolean isHasLowerCase() {
        return hasLowerCase;
    }

    public boolean isHasUpperCase() {
        return hasUpperCase;
    }

    public boolean isHasSpecial() {
        return hasSpecial;
    }

    public boolean isHasWhiteSpace() {
        return hasWhiteSpace;
    }

    public int getNumChars() {
        return numChars;
    }
}