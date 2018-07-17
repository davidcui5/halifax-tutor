package group12.UserSetting;

public class ChangePwdForm {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        if( password != null){
            return " Password: " + password;
        }
        else{
            return "Invalid Changing password Form: Contains NULL";
        }
    }
}
