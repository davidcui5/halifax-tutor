package group12;

import group12.DBConnection.DBDAO;
import group12.Registration.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private DBDAO db = new DBDAO();

    @GetMapping(path = "/user")
    @ResponseBody
    public String login(@RequestBody User user) {
        if (db.authorizeStudent(user.getEmail(), user.getPassword()))
            return "student";
        if (db.authorizeTutor(user.getEmail(), user.getPassword()))
            return "tutor";
        return "not found";
    }

}
