package group12.login;

public class LoginForm {

    private String type;
    private String email;
    private String password;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    //this is to make logging easier
    @Override
    public String toString() {
        if(type != null && email != null && password != null){
            return "Type: " + type + "Email: " + email + "Password " + password;
        }
        else{
            return "Invalid Login Form: Contains NULL";
        }
    }
}
