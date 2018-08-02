package group12.registration;

import group12.data_access.IDataAccessObject;
import group12.data_access.Student;
import group12.data_access.Tutor;
import group12.email.IMailer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class RegistrationService implements IRegister {

    private IDataAccessObject dao;
    private IMailer mailer;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String ERROR = "ERROR";
    private static final String LOGIN_PAGE_URL = "../index.html";
    private static final String CODE_EXPIRED_PAGE = "redirect:/html/code-expired.html";
    private static final String LOGIN_PAGE = "redirect:/index.html";
    private static final String ERROR_PAGE = "redirect:/html/exception-page.html";
    private static final String REPEAT_EMAIL = "Email already registered";
    private static final String REPEAT_PHONE = "Phone already registered";
    private static final String REPEAT_CARD = "Card already registered";

    private static Logger logger = LogManager.getLogger(RegistrationService.class);

    @Value("${email.sender}")
    private String emailSender;

    @Value("${server.url}")
    private String serverURL;

    public void setDao(IDataAccessObject dao) {
        this.dao = dao;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    public void setEmailSender(String emailSender){
        this.emailSender = emailSender;
    }

    public void setServerURL(String serverURL){
        this.serverURL = serverURL;
    }

    public RegistrationResponse registerStudent(Student student) {
        boolean isSuccess = false;
        try {
            if (dao.countOfUserWithEmail(student.getEmail()) != 0) {
                return new RegistrationResponse(FAILURE,REPEAT_EMAIL);
            }
            if (dao.countOfUserWithPhone(student.getPhoneNumber()) != 0) {
                return new RegistrationResponse(FAILURE,REPEAT_PHONE);
            }
            isSuccess = dao.saveStudent(student);
        } catch (Exception e) {
            logger.error(ERROR, e);
        }
        if(isSuccess){
            sendStudentActivationEmail(student.getEmail());
            return new RegistrationResponse(SUCCESS, LOGIN_PAGE_URL);
        }
        else{
            return new RegistrationResponse(FAILURE,ERROR);
        }
    }

    private void sendStudentActivationEmail(String email){
        try {
            int studentID = dao.getStudentIDByEmail(email);
            UUID uuid = UUID.randomUUID();
            if(dao.saveActivationCode(uuid.toString())){
                mailer.sendMail(emailSender, email, "Activation", "Activation " +
                        serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
            }
        } catch (Exception e) {
            logger.error(ERROR, e);
        }
    }

    public RegistrationResponse registerTutor(Tutor tutor) {
        boolean isSuccess = false;
        try {
            if (dao.countOfUserWithEmail(tutor.getEmail()) != 0) {
                return new RegistrationResponse(FAILURE,REPEAT_EMAIL);
            }
            if (dao.countOfUserWithPhone(tutor.getPhoneNumber()) != 0) {
                return new RegistrationResponse(FAILURE,REPEAT_PHONE);
            }
            if (dao.countOfUserWithCreditCardNum(tutor.getCreditCardNum()) != 0){
                return new RegistrationResponse(FAILURE,REPEAT_CARD);
            }
            isSuccess = dao.saveTutor(tutor);
        } catch (Exception e) {
            logger.error(ERROR, e);
        }
        if(isSuccess){
            sendTutorActivationEmail(tutor.getEmail());
            return new RegistrationResponse(SUCCESS, LOGIN_PAGE_URL);
        }
        else{
            return new RegistrationResponse(FAILURE,ERROR);
        }
    }

    public void sendTutorActivationEmail(String email){
        try {
            int tutorID = dao.getTutorIDByEmail(email);
            UUID uuid = UUID.randomUUID();
            if(dao.saveActivationCode(uuid.toString())){
                mailer.sendMail(emailSender, email, "Activation", "Activation " +
                        serverURL + "/tutor/tutorid/" + tutorID + "/activation/" + uuid.toString() + "/");
            }
        } catch (Exception e) {
            logger.error(ERROR, e);
        }
    }

    public String activateStudent(int studentID, String activationCode) {
        try {
            if(dao.checkActivationCode(activationCode) == null){
                return CODE_EXPIRED_PAGE;
            }
            if(dao.setStudentActivatedStatus(studentID, true)){
                return LOGIN_PAGE;
            }
            else{
                return ERROR_PAGE;
            }
        } catch (Exception e) {
            logger.error(studentID + " " + activationCode, e);
        }
        return ERROR_PAGE;
    }

    public String activateTutor(int tutorID, String activationCode) {
        try {
            if(dao.checkActivationCode(activationCode) == null){
                return CODE_EXPIRED_PAGE;
            }
            if(dao.setTutorActivatedStatus(tutorID, true)){
                return LOGIN_PAGE;
            }
            else{
                return ERROR_PAGE;
            }
        } catch (Exception e) {
            logger.error(tutorID + " " + activationCode, e);
        }
        return ERROR_PAGE;
    }
}
