package group12.Registration;

import group12.DBDAO;
import group12.DatabaseInterface;
import group12.Email.IMail;
import group12.Email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class RegistrationService implements IRegister {

    @Autowired
    private DatabaseInterface db = new DBDAO();

    @Autowired
    private IMail mailer = new MailService();

    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;

    public RegistrationResponse registerStudent(Student student) {

        RegistrationResponse response = new RegistrationResponse();
        System.out.println(emailSender); //remove this later
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

    public RegistrationResponse registerTutor(Tutor tutor) {

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
