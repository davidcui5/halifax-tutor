package group12.admin_setting;

import group12.data_access.*;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/admin/setting")
public class AdminController {

    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static Logger logger = LogManager.getLogger(AdminController.class);

    IAccessToken decoder;
    IAdminSettingDAO dao;

    public AdminController(){
        decoder = JWTAccessToken.getInstance();
        dao = new AdminSettingDAO();
    }

    public AdminController(IAccessToken decoder, IAdminSettingDAO dao){
        this.decoder = decoder;
        this.dao = dao;
    }

    @PostMapping(path="/access")
    public String authorizeAdmin(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            return AUTHORIZED;
        }
        else{
            return UNAUTHORIZED;
        }
    }

    private boolean authorizeAdmin(String token){
        String email = decoder.decodeToken(token);
        int count = dao.countAdminByEmail(email);
        if(count == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping(path="/password")
    public String changePassword(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            String email = decoder.decodeToken(body.get("token"));
            logger.log(Level.INFO,email);
            logger.log(Level.INFO,body.get("password"));
            IEncryptor encryptor = new SimpleMD5Encryptor();
            String password = encryptor.encrypt(body.get("password"));
            logger.log(Level.INFO,password);
            if(dao.setAdminPassword(email,password)){
                return SUCCESS;
            }
            else{
                return FAILURE;
            }
        }
        else{
            return UNAUTHORIZED;
        }
    }

    @PostMapping(path="/price")
    public String changePrice(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            float pOne = Float.parseFloat(body.get("priceOne"));
            float pTwo = Float.parseFloat(body.get("priceTwo"));
            float pThree = Float.parseFloat(body.get("priceThree"));
            float pFour = Float.parseFloat(body.get("priceFour"));
            logger.log(Level.INFO,pOne);
            logger.log(Level.INFO,pTwo);
            logger.log(Level.INFO,pThree);
            logger.log(Level.INFO,pFour);
            if(dao.setSubPlanPrice(1,pOne) == Boolean.FALSE){
                return FAILURE;
            }
            if(dao.setSubPlanPrice(2,pTwo) == Boolean.FALSE){
                return FAILURE;
            }
            if(dao.setSubPlanPrice(3,pThree) == Boolean.FALSE){
                return FAILURE;
            }
            if(dao.setSubPlanPrice(4,pFour) == Boolean.FALSE){
                return FAILURE;
            }
            return SUCCESS;
        }
        else{
            return UNAUTHORIZED;
        }

    }

    @PostMapping(path="/ban/student")
    public String banStudent(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            int id = Integer.parseInt(body.get("studentID"));
            logger.log(Level.INFO,id);
            if(dao.setStudentBanStatus(id, true)){
                return SUCCESS;
            }
            else{
                return FAILURE;
            }
        }
        else{
            return UNAUTHORIZED;
        }
    }

    @PostMapping(path="/unban/student")
    public String unbanStudent(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            int id = Integer.parseInt(body.get("studentID"));
            logger.log(Level.INFO,id);
            if(dao.setStudentBanStatus(id, false)){
                return SUCCESS;
            }
            else{
                return FAILURE;
            }
        }
        else{
            return UNAUTHORIZED;
        }

    }

    @PostMapping(path="/ban/tutor")
    public String banTutor(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            int id = Integer.parseInt(body.get("tutorID"));
            logger.log(Level.INFO,id);
            if(dao.setTutorBanStatus(id, true)){
                return SUCCESS;
            }
            else{
                return FAILURE;
            }
        }
        else{
            return UNAUTHORIZED;
        }
    }

    @PostMapping(path="/unban/tutor")
    public String unbanTutor(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            int id = Integer.parseInt(body.get("tutorID"));
            logger.log(Level.INFO,id);
            if(dao.setTutorBanStatus(id, false)){
                return SUCCESS;
            }
            else{
                return FAILURE;
            }
        }
        else{
            return UNAUTHORIZED;
        }
    }

    @PostMapping(path="/find/student")
    public StudentReviews findStudentReviews(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            String email = body.get("email");
            logger.log(Level.INFO,email);

            SQLOperationTemplate op = new GetStudentSQLOperation(email);
            Student s = (Student)op.executeMysqlQuery();

            if(s==null){
                return new StudentReviews(-1,null,null);
            }

            int sID = s.getStudentID();
            logger.log(Level.INFO,sID);

            List<ReviewDTO> reviews = dao.getReviewsMadeByStudent(sID);

            return new StudentReviews(sID, email, reviews);
        }
        else{
            return null;
        }
    }

    @PostMapping(path="/find/tutor")
    public TutorReviews findTutorReviews(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            String email = body.get("email");
            logger.log(Level.INFO,email);

            SQLOperationTemplate op = new GetTutorSQLOperation(email);
            Tutor t = (Tutor)op.executeMysqlQuery();

            if(t==null){
                return new TutorReviews(-1,null,null);
            }
            int tID = t.getTutorID();

            List<ReviewDTO> reviews = dao.getReviewsMadeOnTutors(tID);

            return new TutorReviews(tID, email, reviews);
        }
        else{
            return null;
        }
    }
}
