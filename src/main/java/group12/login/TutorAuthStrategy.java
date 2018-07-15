package group12.login;

import group12.data_access.Tutor;
import group12.data_access.User;

public class TutorAuthStrategy implements IAuthenticationStrategy{
    @Override
    public void authenticate(User tutor) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Tutor validTutor = authDAO.getTutorByEmail(tutor.getEmail());
        if(validTutor == null){
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validTutor.getPassword().equals(tutor.getPassword())){
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.SUCCESS,"Welcome Back " + validTutor.getFirstName()));
        }
        else{
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }
}
