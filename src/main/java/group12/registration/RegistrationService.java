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
    private static final String FAILURE = "FAILURE";
    private static final String SUCCESS = "SUCCESS";
    private static final String CODE_EXPIRED = "Code Expired";
    private static final String LOGIN_PAGE_URL = "../index.html";
    private static Logger logger = LogManager.getLogger(RegistrationService.class);

    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;

    public void setDao(IDataAccessObject dao) {
        this.dao = dao;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    public RegistrationResponse registerStudent(Student student) {
        if (dao.countOfUserWithEmail(student.getEmail()) > 0) {
            return new RegistrationResponse(FAILURE,"Email already registered");
        }
        if (dao.countOfUserWithPhone(student.getPhoneNumber()) > 0) {
            return new RegistrationResponse(FAILURE,"Phone already registered");
        }
        boolean isSuccess = false;
        try {
            isSuccess = dao.saveStudent(student);
        } catch (Exception e) {
            logger.error("Error", e);
        }
        if(isSuccess){
            sendStudentActivationEmail(student.getEmail());
            return new RegistrationResponse(SUCCESS, LOGIN_PAGE_URL);
        }
        else{
            return new RegistrationResponse(FAILURE,"Server Down");
        }
    }

    private void sendStudentActivationEmail(String email){
        try {
            int studentID = dao.getStudentIDByEmail(email);
            UUID uuid = UUID.randomUUID();
            dao.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, email, "Activation",
                    "Activation " + serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    public RegistrationResponse registerTutor(Tutor tutor) {

        if (dao.countOfUserWithEmail(tutor.getEmail()) > 0) {
            return new RegistrationResponse(FAILURE,"Email already registered");
        }
        if (dao.countOfUserWithPhone(tutor.getPhoneNumber()) > 0) {
            return new RegistrationResponse(FAILURE,"Phone already registered");
        }
        if (dao.countOfUserWithCreditCardNum(tutor.getCreditCardNum()) > 0){
            return new RegistrationResponse(FAILURE,"Card already registered");
        }
        boolean isSuccess = false;
        try {
            isSuccess = dao.saveTutor(tutor);
        } catch (Exception e) {
            logger.error("Error", e);
        }
        if(isSuccess){
            sendTutorActivationEmail(tutor.getEmail());
            return new RegistrationResponse(SUCCESS, LOGIN_PAGE_URL);
        }
        else{
            return new RegistrationResponse(FAILURE,"Server Down");
        }
    }

    private void sendTutorActivationEmail(String email){
        try {
            int tutorID = dao.getTutorIDByEmail(email);
            UUID uuid = UUID.randomUUID();
            dao.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, email, "Activation",
                    "Activation " + serverURL + "/tutor/tutorid/" + tutorID + "/activation/" + uuid.toString() + "/");
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    public String activateStudent(int studentID, String activationCode) {
        try {
            if(dao.checkActivationCode(activationCode) == null){
                return CODE_EXPIRED;
            }
            dao.setStudentActivatedStatus(studentID, true);
        } catch (Exception e) {
            logger.error(studentID + " " + activationCode, e);
        }
        return LOGIN_PAGE_URL;
    }

    public String activateTutor(int tutorID, String activationCode) {
        try {
            if(dao.checkActivationCode(activationCode) == null){
                return CODE_EXPIRED;
            }
            dao.setTutorActivatedStatus(tutorID, true);
        } catch (Exception e) {
            logger.error(tutorID + " " + activationCode, e);
        }
        return LOGIN_PAGE_URL;
    }
}
