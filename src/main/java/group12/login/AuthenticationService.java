package group12.login;

import group12.data_access.User;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationService implements IAuthenticator {

    private IAuthDAO authDAO;
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    public AuthenticationService(IAuthDAO authDAO){
        this.authDAO = authDAO;
    }





    @Override
    public LoginResponse authenticate(User user) {

        LoginResponse response = new LoginResponse();

        IEncryptor encryptor = new SimpleMD5Encryptor();
        String type = form.getType();
        String email = form.getEmail();
        String password = encryptor.encrypt(form.getPassword());

        try{
            if(type.equals("student")){
                response = authenticateStudent(email, password);
            }
            else if(type.equals("tutor")){
                response = authenticateTutor(email, password);
            }
            else {
                response = authenticateAdmin(email, password);
            }
        }catch(Exception e){
            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }

        if(response.getResult().equals("SUCCESS")){
            IAccessToken tokenMaker = new JWTAccessToken();
            String token = tokenMaker.generateToken(email);
            response.setToken(token);
        }

        logger.info(form + " " + response);
        return response;
    }

    public LoginResponse authenticateStudent(String email, String password) {
        LoginResponse response = new LoginResponse();
        UserDTO student = authDAO.getStudentByEmail(email);
        if(student == null){
            response.setResult("FAILURE");
            response.setDetail("Wrong email");
            return response;
        }
        else if(student.getPassword().equals(password)){
            response.setResult("SUCCESS");
            if(student.getIsActivated()==true && student.getIsBanned()==false){
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
        UserDTO tutor = authDAO.getTutorByEmail(email);
        if(tutor == null){
            response.setResult("FAILURE");
            response.setDetail("Wrong email");
            return response;
        }
        else if(tutor.getPassword().equals(password)){
            response.setResult("SUCCESS");
            if(tutor.getIsActivated()==true && tutor.getIsBanned()==false){
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
        UserDTO admin = authDAO.getAdminByEmail(email);
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