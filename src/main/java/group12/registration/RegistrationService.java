package group12.registration;

import group12.data_access.IDataAccessObject;
import group12.data_access.Student;
import group12.data_access.Tutor;
import group12.email.IMailer;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class RegistrationService implements IRegister {

    private IDataAccessObject db;
    private IMailer mailer;
    private static Logger logger = LogManager.getLogger(RegistrationService.class);
    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;


    public void setDb(IDataAccessObject db) {
        this.db = db;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    public RegistrationResponse registerStudent(Student student) {
        IEncryptor encryptor = new SimpleMD5Encryptor();
        student.setPassword(encryptor.encrypt(student.getPassword()));

        RegistrationResponse response = new RegistrationResponse();
        if (db.countOfUserWithEmail(student.getEmail()) >= 1) {
            response.setResult("Failure");
            response.addDetail("Email already registered");
        }

        if (db.countOfUserWithPhone(student.getPhoneNumber()) >= 1) {
            response.setResult("Failure");
            response.addDetail("Phone already registered");
        }

        if (response.getResult().equals("Failure")) {
            return response;
        }

        try {
            db.saveStudent(student);
        } catch (Exception e) {
            logger.error(student, e);
            response.setResult("Failure");
            response.addDetail("Internal Server Error, Register Exception");
            return response;
        }

        response.setResult("Success");

        try {
            int studentID = db.getStudentIDByEmail(student.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, student.getEmail(), "Activation",
                    "Activation " + serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
        } catch (Exception e) {
            logger.error(student, e);
            response.addDetail("Cannot Send Activation Email, Please Go To Setting Page to Resend");
        }

        return response;
    }

    public RegistrationResponse registerTutor(Tutor tutor) {

        IEncryptor encryptor = new SimpleMD5Encryptor();
        tutor.setPassword(encryptor.encrypt(tutor.getPassword()));

        RegistrationResponse response = new RegistrationResponse();

        if (db.countOfUserWithEmail(tutor.getEmail()) >= 1) {
            response.setResult("Failure");
            response.addDetail("Email already registered");
        }
        if (db.countOfUserWithPhone(tutor.getPhoneNumber()) >= 1) {
            response.setResult("Failure");
            response.addDetail("Phone already registered");
        }
        if (db.countOfUserWithCreditCardNum(tutor.getCreditCardNum()) >= 1) {
            response.setResult("Failure");
            response.addDetail("Card already registered");
        }

        if (response.getResult().equals("Failure")) {
            return response;
        }
        try {
            db.saveTutor(tutor);
        } catch (Exception e) {
            logger.error(tutor, e);
            response.setResult("Failure");
            response.addDetail("Internal Server Error, Register Exception");
            return response;
        }

        response.setResult("Success");

        try {
            int tutorID = db.getTutorIDByEmail(tutor.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, tutor.getEmail(), "Activation",
                    "Activation " + serverURL + "/tutor/tutorid/" + tutorID + "/activation/" + uuid.toString() + "/");
        } catch (Exception e) {
            logger.error(tutor, e);
            response.addDetail("Cannot Send Activation Email, Please Go To Setting Page to Resend");
        }

        return response;
    }

    public String activateStudent(int studentID, String activationCode) {
        try {
            //todo check activation code
            db.setStudentActivatedStatus(studentID, true);
        } catch (Exception e) {
            logger.error(studentID + " " + activationCode, e);
            return "Activation Failed";
        }
        return "Get a specific Bar with id=" + activationCode +
                " from a Foo with id=" + studentID;
    }

    public String activateTutor(int tutorID, String activationCode) {
        try {
            //todo check activation code
            db.setTutorActivatedStatus(tutorID, true);
        } catch (Exception e) {
            logger.error(tutorID + " " + activationCode, e);
            return "Activation Failed";
        }
        return "Get a specific Bar with id=" + activationCode +
                " from a Foo with id=" + tutorID;
    }
}
