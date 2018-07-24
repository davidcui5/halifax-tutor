package group12.student_setting;

import group12.data_access.IDataAccessObject;
import group12.email.IMailer;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class StudentSettingService implements IStudentSetting {

    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String ACTIVE = "ACTIVE";
    private static final String NOT_ACTIVE = "NOT_ACTIVE";

    private static Logger logger = LogManager.getLogger(StudentSettingService.class);

    private IAccessToken decoder;
    private IDataAccessObject db;
    private IMailer mailer;


    @Value("${email.sender}")
    private String emailSender;

    @Value("${server.url}")
    private String serverURL;


    public StudentSettingService() {
        decoder = JWTAccessToken.getInstance();
    }

    public void setDb(IDataAccessObject db) {
        this.db = db;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    private boolean isAuthorized(String token) throws Exception {
        try {
            boolean result = false;
            String email = decoder.decodeToken(token);
            int count = db.countOfUserWithEmail(email);
            if (count == 1) {
                result = true;
            }
            return result;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public String authorizeStudent(String token) throws Exception {
        try {
            if (isAuthorized(token))
                return AUTHORIZED;
            else return UNAUTHORIZED;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }


    @Override
    public String changePassword(String token, String password) throws Exception {
        try {
            if (isAuthorized(token)) {
                String email = decoder.decodeToken(token);
                logger.log(Level.INFO, email);
                IEncryptor encryptor = new SimpleMD5Encryptor();
                String encryptPassword = encryptor.encrypt(password);
                logger.log(Level.INFO, encryptPassword);
                if (db.updateStudentPassword(email, encryptPassword)) {
                    return SUCCESS;
                } else {
                    return FAILURE;
                }
            } else {
                return UNAUTHORIZED;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }


    @Override
    public String changeEmail(String token, String email) throws Exception {
        try {
            if (isAuthorized(token)) {
                String oldEmail = decoder.decodeToken(token);
                logger.log(Level.INFO, email);
                String newEmail = email;
                logger.log(Level.INFO, newEmail);
                if (db.updateStudentEmail(oldEmail, newEmail)) {
                    return SUCCESS;
                } else {
                    return FAILURE;
                }
            } else {
                return UNAUTHORIZED;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public String resendActivateCode(String token) throws Exception {
        try {
            if (isAuthorized(token)) {
                String email = decoder.decodeToken(token);
                int studentID = db.getStudentIDByEmail(email);
                UUID uuid = UUID.randomUUID();
                db.saveActivationCode(uuid.toString());
                mailer.sendMail(emailSender, email, "Activation",
                        "Activation " + serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
                return "The activation link sent your email.";
            } else {
                return UNAUTHORIZED;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public String checkActivationStatus(String token) throws Exception {
        try {
            if (isAuthorized(token)) {
                String email = decoder.decodeToken(token);
                logger.log(Level.INFO, email);
                int studentActivationStatus = db.getStudentActivationStatus(email);
                if (studentActivationStatus == 1)
                    return ACTIVE;
                else
                    return NOT_ACTIVE;
            } else {
                return UNAUTHORIZED;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public String changePhone(String token, String phone) throws Exception {
        try {
            if (isAuthorized(token)) {
                String email = decoder.decodeToken(token);
                logger.log(Level.INFO, email);
                logger.log(Level.INFO, phone);
                if (db.updateStudentPhone(email, phone)) {
                    return SUCCESS;
                } else {
                    return FAILURE;
                }
            } else {
                return UNAUTHORIZED;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
