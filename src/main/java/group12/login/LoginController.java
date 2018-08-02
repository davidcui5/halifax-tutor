package group12.login;

import group12.data_access.*;
import group12.encryption.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    private Map<String,IAuthenticationStrategy> strategies;

    @Autowired
    public LoginController(Map<String,IAuthenticationStrategy> strategies){
        this.strategies = strategies;
    }

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody Map<String,String> body){
        User user = makeUser(body);
        String type = body.get("type");
        IAuthenticationStrategy authStrategy = strategies.get(type);
        authStrategy.authenticate(user);
        return user.getLoginResponse();
    }

    private User makeUser(Map<String,String> body){
        String type = body.get("type");
        String email = body.get("email");
        IEncryptor encryptor = SimpleMD5Encryptor.getInstance();
        String password = encryptor.encrypt(body.get("password"));
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
