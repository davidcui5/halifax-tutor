package group12.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private IAuthenticator authService;

    @Autowired
    public LoginController() {
        authService = new AuthenticationService();
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public LoginResponse login(@RequestBody LoginForm form){
        LoginResponse response = authService.authenticate(form);
        return response;
    }

}
