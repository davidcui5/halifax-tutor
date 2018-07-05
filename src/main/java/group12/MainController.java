package group12;

import group12.Email.MailMail;
import group12.Registration.Tutor;
import group12.Registration.User;
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

    @Value("${server.url}")
    String serverURL;

    @GetMapping(path = "/user")
    public @ResponseBody String login(@RequestBody User user) {
        if (db.authorizeStudent(user.getEmail(), user.getPassword()))
            return "student";
        if (db.authorizeTutor(user.getEmail(), user.getPassword()))
            return "tutor";
        return "not found";
    }



    @RequestMapping(value = "/student/activate/{email}")
    @ResponseBody
    public String sendActivationEmail(@PathVariable String email) {

        return "Sent email to this email" + email;
    }


    @PostMapping(path = "/tutor")
    public @ResponseBody String teacherRegister(@RequestBody Tutor tutor) {

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

    @RequestMapping(value = "/student/studentid/{studentid}/activation/{activationcode}/", method = GET)
    @ResponseBody
    public String activateStudent(@PathVariable int studentid, @PathVariable String activationcode) {
        db.activateStudent(studentid, activationcode);
        return "Get a specific Bar with id=" + activationcode +
                " from a Foo with id=" + studentid;
    }

    @RequestMapping(value = "/tutor/tutorid/{tutorid}/activation/{activationcode}/", method = GET)
    @ResponseBody
    public String activateTutor(@PathVariable int tutorid, @PathVariable String activationcode) {
        db.activateTutor(tutorid, activationcode);
        return "Get a specific Bar with id=" + activationcode +
                " from a Foo with id=" + tutorid;
    }
}
