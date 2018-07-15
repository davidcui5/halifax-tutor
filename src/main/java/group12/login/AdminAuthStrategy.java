package group12.login;

import group12.data_access.Admin;
import group12.data_access.User;

public class AdminAuthStrategy implements IAuthenticationStrategy {
    @Override
    public LoginResponse authenticate(User admin) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Admin validAdmin = authDAO.getAdminByEmail(admin.getEmail());
        if(validAdmin == null){
            return new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email");
        }
        else if(validAdmin.getPassword().equals(admin.getPassword())){
            return new LoginResponse(AuthenticationResult.SUCCESS,"Welcome Back Admin");
        }
        else{
            return new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password");
        }
    }
}