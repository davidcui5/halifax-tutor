package group12.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private IAuthenticator authService;
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    public LoginController(IAuthenticator authService){
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public LoginResponse login(@RequestBody LoginForm form){
        try{
            LoginResponse response = authService.authenticate(form);
            return response;
        } catch(Exception e){
            logger.error("ERROR",e);
            LoginResponse response = new LoginResponse();
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
            logger.info(form + " " + response);
            return response;
        }
    }
}
