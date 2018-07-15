package group12.login;

import group12.data_access.Admin;
import group12.data_access.User;

public class AdminAuthStrategy implements IAuthenticationStrategy {
    @Override
    public void authenticate(User admin) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Admin validAdmin = authDAO.getAdminByEmail(admin.getEmail());
        if(validAdmin == null){
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validAdmin.getPassword().equals(admin.getPassword())){
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.SUCCESS,"Welcome Back Admin"));
        }
        else{
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }
}