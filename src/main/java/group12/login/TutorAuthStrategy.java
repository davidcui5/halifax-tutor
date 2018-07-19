package group12.login;

import group12.data_access.Tutor;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class TutorAuthStrategy implements IAuthenticationStrategy{
    private static final String SEARCH_PAGE_URL = "html/search-tutor.html";
    private static final String TUTOR_SETTING_PAGE_URL = "html/tutor-setting-page.html";

    @Override
    public void authenticate(User tutor) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Tutor validTutor = authDAO.getTutorByEmail(tutor.getEmail());
        if(validTutor == null){
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validTutor.getPassword().equals(tutor.getPassword())){
            AuthenticationResult result = AuthenticationResult.SUCCESS;
            String message = "Welcome Back " + validTutor.getFirstName();
            String url = makeUrl(validTutor.isActivated(),validTutor.isBanned());
            String token = makeToken(tutor.getEmail());
            tutor.setLoginResponse(new LoginResponse(result, message, url, token));
        }
        else{
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }

    private String makeToken(String email){
        IAccessToken tokenMaker = new JWTAccessToken();
        return tokenMaker.generateToken(email);
    }

    private String makeUrl(boolean isActivated, boolean isBanned){
        if(isBanned){
            return TUTOR_SETTING_PAGE_URL;
        }
        else if(isActivated){
            return SEARCH_PAGE_URL;
        }
        else{
            return TUTOR_SETTING_PAGE_URL;
        }
    }
}
