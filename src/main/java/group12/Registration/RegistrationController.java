package group12.Registration;

import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private IRegister registerService;

    //uses constructor to instantiate registerService
    //I am aware Spring has some cool way to do it using autowired and injection
    //but I wanted to limit on the use of Spring.
    //can change the concrete RegistrationService in case logic changes.
    public RegistrationController(){
        registerService = new RegistrationService();
    }

    @PostMapping(path = "/student")
    @ResponseBody
    public RegistrationResponse registerStudent(@RequestBody Student student){
        RegistrationResponse response = registerService.registerStudent(student);
        return response;
    }

    @PostMapping(path = "/tutor")
    @ResponseBody
    public RegistrationResponse registerTutor(@RequestBody Tutor tutor){
        RegistrationResponse response = registerService.registerTutor(tutor);
        return response;
    }

    //look into this later
    @RequestMapping(value = "/student/activate/{email}")
    @ResponseBody
    public String sendActivationEmail(@PathVariable String email) {

        return "Sent email to this email" + email;
    }

    //look into
    @GetMapping(value = "/student/studentid/{studentid}/activation/{activationcode}/")
    @ResponseBody
    public String activateStudent(@PathVariable int studentid, @PathVariable String activationcode) {
        String response = registerService.activateStudent(studentid, activationcode);
        return response;
    }

    //look into
    @GetMapping(value = "/tutor/tutorid/{tutorid}/activation/{activationcode}/")
    @ResponseBody
    public String activateTutor(@PathVariable int tutorid, @PathVariable String activationcode) {
        String response = registerService.activateTutor(tutorid, activationcode);
        return response;
    }

}
