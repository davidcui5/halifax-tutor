package group12.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /*@Autowired
    private IAuthenticator auth = new AuthenticationService();*/

    @GetMapping(path = "/login")
    @ResponseBody
    public LoginResponse login(@RequestBody LoginForm form){
        IAuthenticator auth = new AuthenticationService();
        LoginResponse response = auth.authenticate(form);
        return response;
    }

}
