package group12.login;

import group12.data_access.Student;
import group12.data_access.User;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthStrategy implements IAuthenticationStrategy {

    @Value("${login.bannedStudentGoTo}")
    String bannedStudentGoTo;
    @Value("${login.inactiveStudentGoTo}")
    String inactiveStudentGoTo;
    @Value("${login.activeAndUnbannedStudentGoTo}")
    String activeAndUnbannedStudentGoTo;

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
            return bannedStudentGoTo;
        }
        else if(isActivated){
            return activeAndUnbannedStudentGoTo;
        }
        else{
            return inactiveStudentGoTo;
        }
    }
}