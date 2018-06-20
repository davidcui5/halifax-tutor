package group12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainController {

    @Autowired
    private DBDAO db = new DBDAO();

    @Autowired
    private MailMail m = new MailMail();


    @Value("${email.sender}")
    String emailSender;

    @GetMapping(path = "/user")
    public @ResponseBody
    String login(@RequestBody User user) {
        if (db.authorizeStudent(user.getEmail(), user.getPassword()))
            return "student";
        if (db.authorizeTutor(user.getEmail(), user.getPassword()))
            return "tutor";
        return "not found";
    }

    @PostMapping(path = "/student")
    public @ResponseBody
    String studentRegister(@RequestBody Student student) {
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
                    "<a href=http://localhost:8080/student/studentid/" + studentID + "/activation/" + uuid.toString() + "/>Activation</a>");
            return "registration success";
        } else
            return response;
    }

    @RequestMapping(value = "/student/activate/{email}")
    @ResponseBody
    public String sendActivationEmail(@PathVariable String email) {

        return "Sent email to this email" + email;
    }


    @PostMapping(path = "/tutor")
    public @ResponseBody
    String teacherRegister(@RequestBody Tutor tutor) {

        String response = "";

        if (!db.isEmailNew(tutor.getEmail()))
            response += "Email already registered\n";
        if (!db.isPhoneNumberNew(tutor.getPhoneNumber()))
            response += "Phone already registered\n";
        if (!db.isCreditCardNew(tutor.getCreditCardNumber()))
            response += "Card already registered";

        if (response.equals("")) {
            db.regTutor(tutor);
            return "registration success";
        } else
            return response;
    }

    @RequestMapping(value = "/student/studentid/{studentid}/activation/{activationcode}/", method = GET)
    @ResponseBody
    public String activateStudent
            (@PathVariable String studentid, @PathVariable String activationcode) {
        return "Get a specific Bar with id=" + activationcode +
                " from a Foo with id=" + studentid;
    }
}
