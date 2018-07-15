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
        IEncryptor encryptor = new SimpleMD5Encryptor();
        String password = encryptor.encrypt(form.getPassword());
        if(type.equals("student")){
            user = new Student(email, password);
        }
        else if(type.equals("tutor")){
            user = new Tutor(email, password);
        }
        else {
            user = new Admin(email, password);
        }

        authService.authenticate(user);

        LoginResponse response = user.getLoginResponse();
        if(response.getResult() == AuthenticationResult.SUCCESS){
            IAccessToken tokenMaker = new JWTAccessToken();
            String token = tokenMaker.generateToken(email);
            response.setToken(token);
        }

        redirectionService.redirect(User user);

        return response;
    }
}
