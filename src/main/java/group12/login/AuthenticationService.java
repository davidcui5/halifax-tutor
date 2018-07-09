package group12.login;

import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class AuthenticationService implements IAuthenticator {

    private IAuthDAO DAO = new MysqlAuthDAO();

    @Override
    public LoginResponse authenticate(LoginForm form) {

        IEncryptor encryptor = new SimpleMD5Encryptor();
        String type = form.getType();
        String email = form.getEmail();
        String password = encryptor.encrypt(form.getPassword());
        LoginResponse response;

        if(type.equals("student")){
            response = authenticateStudent(email, password);
        }
        else if(type.equals("tutor")){
            response = authenticateTutor(email, password);
        }
        else {
            response = authenticateAdmin(email, password);
        }

        if(response.getResult().equals("Success")){
            IAccessToken tokenMaker = new JWTAccessToken();
            String token = tokenMaker.generateToken(email);
            response.setToken(token);
        }

        return response;
    }

    public LoginResponse authenticateStudent(String email, String password) {
        LoginResponse response = new LoginResponse();
        if(DAO.isStudentEmailWrong(email)){
            response.setResult("Failure");
            response.setDetail("Wrong email");
            return response;
        }
        else if(DAO.getStudentPassword(email).equals(password)){
            response.setResult("Success");
            if(DAO.isStudentNotActivated(email) || DAO.isStudentBanned(email)){
                response.setUrl("html/student-setting-page.html");
            }
            else{
                response.setUrl("html/search-tutor.html");
            }
            return response;
        }
        else{
            response.setResult("Failure");
            response.setDetail("Wrong password");
            return response;
        }
    }

    public LoginResponse authenticateTutor(String email, String password) {
        LoginResponse response = new LoginResponse();
        if(DAO.isTutorEmailWrong(email)){
            response.setResult("Failure");
            response.setDetail("Wrong email");
            return response;
        }
        else if(DAO.getTutorPassword(email).equals(password)){
            response.setResult("Success");
            if(DAO.isTutorNotActivated(email) || DAO.isTutorBanned(email)){
                response.setUrl("html/tutor-setting-page.html"); //change this to the right file name
            }
            else{
                response.setUrl("html/search-tutor.html");
            }
            return response;
        }
        else{
            response.setResult("Failure");
            response.setDetail("Wrong password");
            return response;
        }
    }

    public LoginResponse authenticateAdmin(String email, String password) {
        LoginResponse response = new LoginResponse();
        if(DAO.isAdminEmailWrong(email)){
            response.setResult("Failure");
            response.setDetail("Wrong email");
            return response;
        }
        else if(DAO.getAdminPassword(email).equals(password)){
            response.setResult("Success");
            response.setUrl("html/search-tutor.html");
            return response;
        }
        else{
            response.setResult("Failure");
            response.setDetail("Wrong password");
            return response;
        }
    }
}
