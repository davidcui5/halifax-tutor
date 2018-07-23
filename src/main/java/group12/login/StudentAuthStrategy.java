package group12.login;

import group12.data_access.Student;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class StudentAuthStrategy implements IAuthenticationStrategy {
    private static final String SEARCH_PAGE_URL = "html/search-tutor.html";
    private static final String STUDENT_SETTING_PAGE_URL = "html/student-setting.html";

    @Override
    public void authenticate(User student) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Student validStudent = authDAO.getStudentByEmail(student.getEmail());
        if(validStudent == null){
            student.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validStudent.getPassword().equals(student.getPassword())){
            AuthenticationResult result = AuthenticationResult.SUCCESS;
            String message = "Welcome Back, " + validStudent.getFirstName();
            String url = makeUrl(validStudent.isActivated(),validStudent.isBanned());
            String token = makeToken(student.getEmail());
            student.setLoginResponse(new LoginResponse(result, message, url, token));
        }
        else{
            student.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }

    private String makeToken(String email){
        IAccessToken tokenMaker = JWTAccessToken.getInstance();
        return tokenMaker.generateToken(email);
    }

    private String makeUrl(boolean isActivated, boolean isBanned){
        if(isBanned){
            return STUDENT_SETTING_PAGE_URL;
        }
        else if(isActivated){
            return SEARCH_PAGE_URL;
        }
        else{
            return STUDENT_SETTING_PAGE_URL;
        }
    }
}