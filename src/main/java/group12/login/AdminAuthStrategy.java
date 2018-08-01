package group12.login;

import group12.data_access.Admin;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class AdminAuthStrategy implements IAuthenticationStrategy {

    private IAuthDAO authDAO;

    private String adminGoTo;

    public void setAuthDAO(IAuthDAO authDAO){
        this.authDAO = authDAO;
    }

    public void setAdminGoTo(String adminGoTo) {
        this.adminGoTo = adminGoTo;
    }

    @Override
    public void authenticate(User admin) {
        Admin validAdmin = authDAO.getAdminByEmail(admin.getEmail());
        if(validAdmin == null){
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validAdmin.getPassword().equals(admin.getPassword())){
            AuthenticationResult result = AuthenticationResult.SUCCESS;
            String message = "Welcome Back Admin";
            String url = adminGoTo;
            String token = makeToken(admin.getEmail());
            admin.setLoginResponse(new LoginResponse(result, message, url, token));
        }
        else{
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }

    //helper for making token
    private String makeToken(String email){
        IAccessToken tokenMaker = JWTAccessToken.getInstance();
        return tokenMaker.generateToken(email);
    }
}