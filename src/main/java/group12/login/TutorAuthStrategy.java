package group12.login;

import group12.data_access.Tutor;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TutorAuthStrategy implements IAuthenticationStrategy{

    @Value("${login.bannedTutorGoTo}")
    String bannedTutorGoTo;
    @Value("${login.inactiveTutorGoTo}")
    String inactiveTutorGoTo;
    @Value("${login.activeAndUnbannedTutorGoTo}")
    String activeAndUnbannedTutorGoTo;

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
        IAccessToken tokenMaker = JWTAccessToken.getInstance();
        return tokenMaker.generateToken(email);
    }

    private String makeUrl(boolean isActivated, boolean isBanned){
        if(isBanned){
            return bannedTutorGoTo;
        }
        else if(isActivated){
            return inactiveTutorGoTo;
        }
        else{
            return activeAndUnbannedTutorGoTo;
        }
    }
}
