package group12.login;

public class UserDTO {

    String password;
    boolean isActivated;
    boolean isBanned;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public boolean getIsBanned() {
        return isBanned;
    }
}
