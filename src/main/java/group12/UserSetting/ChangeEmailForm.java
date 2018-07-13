package group12.UserSetting;

public class ChangeEmailForm {
    private String email;



    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        if( email != null){
            return " Email: " + email;
        }
        else{
            return "Invalid Changing email Form: Contains NULL";
        }
    }
}
