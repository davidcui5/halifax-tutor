package group12.login;

import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;

public class AuthenticationService implements IAuthenticator {

    private IAuthDAO DAO;

    public void setDAO(IAuthDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public LoginResponse authenticate(LoginForm form) {

        /*IEncryptor encryptor = new SimpleMD5Encryptor();*/
        String type = form.getType();
        String email = form.getEmail();
        /*String password = encryptor.encrypt(form.getPassword());*/
        String password = form.getPassword();
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

        if(response.getResult().equals("SUCCESS")){
            IAccessToken tokenMaker = new JWTAccessToken();
            String token = tokenMaker.generateToken(email);
            response.setToken(token);
        }

        return response;
    }

    public LoginResponse authenticateStudent(String email, String password) {
        LoginResponse response = new LoginResponse();
        UserDTO student = DAO.getStudentByEmail(email);
        if(student == null){
            response.setResult("FAILURE");
            response.setDetail("Wrong email");
            return response;
        }
        else if(student.getPassword().equals(password)){
            response.setResult("SUCCESS");
            if(student.getIsActivated()==true || student.getIsBanned()==false){
                response.setUrl("html/search-tutor.html");
            }
            else{
                response.setUrl("html/student-setting-page.html");
            }
            return response;
        }
        else{
            response.setResult("FAILURE");
            response.setDetail("Wrong password");
            return response;
        }
    }

    public LoginResponse authenticateTutor(String email, String password) {
        LoginResponse response = new LoginResponse();
        UserDTO tutor = DAO.getTutorByEmail(email);
        if(tutor == null){
            response.setResult("FAILURE");
            response.setDetail("Wrong email");
            return response;
        }
        else if(tutor.getPassword().equals(password)){
            response.setResult("SUCCESS");
            if(tutor.getIsActivated()==true || tutor.getIsBanned()==false){
                response.setUrl("html/search-tutor.html");
            }
            else{
                response.setUrl("html/tutor-setting-page.html");
            }
            return response;
        }
        else{
            response.setResult("FAILURE");
            response.setDetail("Wrong password");
            return response;
        }
    }

    public LoginResponse authenticateAdmin(String email, String password) {
        LoginResponse response = new LoginResponse();
        UserDTO admin = DAO.getAdminByEmail(email);
        if(admin == null){
            response.setResult("FAILURE");
            response.setDetail("Wrong email");
            return response;
        }
        else if(admin.getPassword().equals(password)){
            response.setResult("SUCCESS");
            response.setUrl("html/search-tutor.html");
            return response;
        }
        else{
            response.setResult("FAILURE");
            response.setDetail("Wrong password");
            return response;
        }
    }
}