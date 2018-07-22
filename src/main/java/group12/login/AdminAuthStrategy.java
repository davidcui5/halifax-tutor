package group12.login;

import group12.data_access.Admin;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class AdminAuthStrategy implements IAuthenticationStrategy {
    private static final String SEARCH_PAGE_URL = "html/search-tutor.html";

    @Override
    public void authenticate(User admin) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Admin validAdmin = authDAO.getAdminByEmail(admin.getEmail());
        if(validAdmin == null){
            admin.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validAdmin.getPassword().equals(admin.getPassword())){
            AuthenticationResult result = AuthenticationResult.SUCCESS;
            String message = "Welcome Back Admin";
            String url = SEARCH_PAGE_URL;
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