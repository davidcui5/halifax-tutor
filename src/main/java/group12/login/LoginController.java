package group12.login;

import group12.data_access.*;
import group12.encryption.*;
import group12.token_auth.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//the login controller controls login
@RestController
public class LoginController {

    //processes login
    //context for authentication strategy pattern
    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginForm form){
        User user = makeUser(form);
        IAuthenticationStrategy authStrategy = user.createAuthenticationStrategy();
        authStrategy.authenticate(user);
        return user.getLoginResponse();
    }

    //helper method makes User from LoginForm
    public User makeUser(LoginForm form){
        String type = form.getType();
        String email = form.getEmail();
        IEncryptor encryptor = new SimpleMD5Encryptor();
        String password = encryptor.encrypt(form.getPassword());
        if(type.equals("student")){
            return new Student(email, password);
        }
        else if(type.equals("tutor")){
            return new Tutor(email, password);
        }
        else {
            return new Admin(email, password);
        }
    }
}
