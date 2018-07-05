package group12.Registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegistrationController {

    @PostMapping(path = "/student")
    public RegistrationResponse registerStudent(@RequestBody Student student){

    }
    @PostMapping(path = "/student")


    public RegistrationResponse studentRegister(@RequestBody Student student) {
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

}
