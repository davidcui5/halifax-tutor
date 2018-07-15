package group12.login;


import group12.data_access.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//the login controller controls login
@RestController
public class LoginController {

    private IAuthenticator authService;

    public LoginController(IAuthenticator authService){
        this.authService = authService;
    }

    //processes login
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginForm form){
        //turns frontend form into backend User object
        User user;
        String type = form.getType();
        String email = form.getEmail();
        String password = form.getPassword();
        if(type.equals("student")){
            user = new Student(email, password);
        }
        else if(type.equals("tutor")){
            user = new Tutor(email, password);
        }
        else {
            user = new Admin(email, password);
        }

        LoginResponse response;

    }
}
