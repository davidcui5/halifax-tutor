package group12.student_setting;

import group12.admin_setting.AdminController;
import group12.admin_setting.AdminSettingDAO;
import group12.admin_setting.IAdminSettingDAO;
import group12.data_access.IDataAccessObject;
import group12.data_access.MysqlDAOImpl;
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

import java.util.Map;

@RestController
@RequestMapping(path = "/student/setting")
public class StudentController {
    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static Logger logger = LogManager.getLogger(StudentController.class);

    IAccessToken decoder;
    IStudentSettingDAO dao;
    IDataAccessObject dbao;

    public StudentController() {
        decoder = JWTAccessToken.getInstance();
        dao = new StudentSettingDAO();
        dbao = new MysqlDAOImpl();
    }

    public StudentController(IAccessToken decoder, IStudentSettingDAO dao) {
        this.decoder = decoder;
        this.dao = dao;
    }

    private boolean authorizeAdmin(String token) {
        String email = decoder.decodeToken(token);
        int count = dbao.countOfUserWithEmail(email);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(path = "/access")
    public String authorizeAdmin(@RequestBody Map<String, String> body) {
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if (isAuthorized) {
            return AUTHORIZED;
        } else {
            return UNAUTHORIZED;
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
            if(dbao.updateStudentPassword(email,password)){
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
}
