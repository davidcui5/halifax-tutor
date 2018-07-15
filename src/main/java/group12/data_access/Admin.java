package group12.data_access;

import group12.login.AdminAuthStrategy;
import group12.login.AdminRedirectStrategy;
import group12.login.IAuthenticationStrategy;
import group12.login.IRedirectionStrategy;

public class Admin extends User {

    public Admin(){
        super();
    }

    public Admin(String email, String password){
        super(email, password);
    }

    @Override
    public IAuthenticationStrategy createAuthenticationStrategy() {
        return new AdminAuthStrategy();
    }

    @Override
    public IRedirectionStrategy createRedirectionStrategy() {
        return new AdminRedirectStrategy();
    }
}
