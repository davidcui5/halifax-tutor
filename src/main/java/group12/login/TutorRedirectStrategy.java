package group12.login;

import group12.data_access.Tutor;
import group12.data_access.User;

public class TutorRedirectStrategy implements IRedirectionStrategy{
    @Override
    public void redirect(User tutor) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Tutor validTutor = authDAO.getTutorByEmail(tutor.getEmail());
        LoginResponse response = tutor.getLoginResponse();
        if(response.getResult()==AuthenticationResult.SUCCESS){
            if(validTutor.isActivated() && !validTutor.isBanned()){
                response.setUrl("html/search-tutor.html");
            }
            else{
                response.setUrl("html/tutor-setting-page.html");
            }
        }
        else {
            response.setUrl(".");
        }
        tutor.setLoginResponse(response);
    }
}
