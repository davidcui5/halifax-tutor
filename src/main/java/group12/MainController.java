package group12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private DBDAO db = new DBDAO();

    @Autowired
    private MailMail m = new MailMail();


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

        if (db.isEmailNew(student.getEmail()))
            response += "Email already registered\n";
        if (db.isPhoneNumberNew(student.getPhoneNumber()))
            response += "Phone already registered\n";

        if (response.equals("")) {
            db.regStudent(student);
            m.sendMail("zaher88abd@gmail.com", student.getEmail(), "Activation", "Plase activate the email");
            return "registration success";
        } else
            return response;
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
}
