package group12.Registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegistrationController {

    private IRegister registerService;

    //uses constructor to instantiate registerService
    //I am aware Spring has some cool way to do it using autowired and injection
    //but I wanted to limit on the use of Spring.
    //can change the concrete RegistrationService in case logic changes.
    public RegistrationController(){
        this.registerService = new RegistrationService();
    }

    @PostMapping(path = "/student")
    public RegistrationResponse registerStudent(@RequestBody Student student){
        RegistrationResponse response = registerService.registerStudent(student);
        return response;
    }

    @PostMapping(path = "/tutor")
    public RegistrationResponse registerTutor(@RequestBody Tutor tutor){
        RegistrationResponse response = registerService.registerTutor(tutor);
        return response;
    }

}
