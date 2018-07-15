package group12.data_access;

import group12.login.IAuthenticationStrategy;
import group12.login.IAuthenticationStrategyFactoryMethod;
import group12.login.LoginResponse;

public abstract class User implements IAuthenticationStrategyFactoryMethod {

    String email;
    String password;
    LoginResponse loginResponse;

    public User(){
        email = "";
        password = "";
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public abstract IAuthenticationStrategy createAuthenticationStrategy();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
