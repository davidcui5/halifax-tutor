package group12.login;

import group12.dataaccess.Student;
import group12.dataaccess.User;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;

public class StudentAuthStrategy implements IAuthenticationStrategy {

    private IAuthDAO authDAO;
    private String bannedStudentGoTo;
    private String inactiveStudentGoTo;
    private String activeAndUnbannedStudentGoTo;

    public void setAuthDAO(IAuthDAO authDAO){
        this.authDAO = authDAO;
    }

    public void setBannedStudentGoTo(String bannedStudentGoTo) {
        this.bannedStudentGoTo = bannedStudentGoTo;
    }

    public void setInactiveStudentGoTo(String inactiveStudentGoTo) {
        this.inactiveStudentGoTo = inactiveStudentGoTo;
    }

    public void setActiveAndUnbannedStudentGoTo(String activeAndUnbannedStudentGoTo) {
        this.activeAndUnbannedStudentGoTo = activeAndUnbannedStudentGoTo;
    }

    @Override
    public void authenticate(User student) {
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
        if(isActivated){
            return activeAndUnbannedStudentGoTo;
        }
        return inactiveStudentGoTo;
    }
}