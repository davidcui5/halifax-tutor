package group12.registration;

import group12.DBDAO;
import group12.DatabaseInterface;
import group12.email.IMailer;
import group12.email.MailerService;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class RegistrationService implements IRegister {

    private DatabaseInterface db;
    private IMailer mailer;

    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;


    public void setDb(DatabaseInterface db) {
        this.db = db;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    public RegistrationResponse registerStudent(StudentSignupForm student) {
        IEncryptor encryptor = new SimpleMD5Encryptor();
        student.setPassword(encryptor.encrypt(student.getPassword()));

        RegistrationResponse response = new RegistrationResponse();
        if (db.isEmailNew(student.getEmail())){
            response.setResult("Failure");
            response.addDetail("Email already registered");
        }

        if (db.isPhoneNumberNew(student.getPhoneNumber())){
            response.setResult("Failure");
            response.addDetail("Phone already registered");
        }

        if(response.getResult().equals("Failure")){
            return response;
        }
        else{
            db.regStudent(student);
            int studentID = db.getStudentId(student.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, student.getEmail(), "Activation",
                    "Activation " + serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
            response.setResult("Success");
            return response;
        }
    }

    public RegistrationResponse registerTutor(TutorSignupForm tutor) {

        IEncryptor encryptor = new SimpleMD5Encryptor();
        tutor.setPassword(encryptor.encrypt(tutor.getPassword()));

        RegistrationResponse response = new RegistrationResponse();

        if (db.isEmailNew(tutor.getEmail())){
            response.setResult("Failure");
            response.addDetail("Email already registered");
        }
        if (db.isPhoneNumberNew(tutor.getPhoneNumber())){
            response.setResult("Failure");
            response.addDetail("Phone already registered");
        }
        if (db.isCreditCardNew(tutor.getCreditCardNumber())){
            response.setResult("Failure");
            response.addDetail("Card already registered");
        }

        if(response.getResult().equals("Failure")){
            return response;
        }
        else{
            db.regTutor(tutor);
            int tutorID = db.getTutorID(tutor.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            mailer.sendMail(emailSender, tutor.getEmail(), "Activation",
                    "Activation " + serverURL + "/tutor/tutorid/" + tutorID + "/activation/" + uuid.toString() + "/");
            response.setResult("Success");
            return response;
        }
    }

    public String activateStudent(int studentID, String activationCode) {
        db.activateStudent(studentID, activationCode);
        return "Get a specific Bar with id=" + activationCode +
                " from a Foo with id=" + studentID;
    }

    public String activateTutor(int tutorID, String activationCode) {
        db.activateTutor(tutorID, activationCode);
        return "Get a specific Bar with id=" + activationCode +
                " from a Foo with id=" + tutorID;
    }
}
