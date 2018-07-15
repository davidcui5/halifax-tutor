package group12.login;

import group12.data_access.User;

public class AdminRedirectStrategy implements IRedirectionStrategy {
    @Override
    public void redirect(User admin) {
        LoginResponse response = admin.getLoginResponse();
        if(response.getResult()==AuthenticationResult.SUCCESS){
            response.setUrl("html/search-tutor.html");
        }
        else {
            response.setUrl(".");
        }
        admin.setLoginResponse(response);
    }
}
