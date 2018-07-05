package group12.Registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

public class RegistrationService implements IRegister {

    public RegistrationResponse registerStudent(@RequestBody Student student) {
        String response = "";
        System.out.println(emailSender);
        if (db.isEmailNew(student.getEmail()))
            response += "Email already registered\n";
        if (db.isPhoneNumberNew(student.getPhoneNumber()))
            response += "Phone already registered\n";

        if (response.equals("")) {
            db.regStudent(student);
            int studentID = db.getStudentId(student.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            m.sendMail(emailSender, student.getEmail(), "Activation",
                    "Activation " + serverURL + "/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/");
            return "registration success";
        } else
            return response;
    }

    public RegistrationResponse registerTutor(@RequestBody Tutor tutor) {

        String response = "";

        if (db.isEmailNew(tutor.getEmail()))
            response += "Email already registered\n";
        if (db.isPhoneNumberNew(tutor.getPhoneNumber()))
            response += "Phone already registered\n";
        if (db.isCreditCardNew(tutor.getCreditCardNumber()))
            response += "Card already registered";

        if (response.equals("")) {
            db.regTutor(tutor);
            int tutorID = db.getTutorID(tutor.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            m.sendMail(emailSender, tutor.getEmail(), "Activation",
                    "Activation " + serverURL + "/tutor/tutorid/" + tutorID + "/activation/" + uuid.toString() + "/");
            return "registration success";
        } else
            return response;
    }



}
