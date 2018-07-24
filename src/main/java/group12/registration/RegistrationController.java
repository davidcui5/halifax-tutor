package group12.registration;

import group12.data_access.Student;
import group12.data_access.Tutor;
import group12.encryption.IEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private IRegister registerService;
    @Autowired
    private IEncryptor encryptor;

    @PostMapping(path = "/student")
    @ResponseBody
    public RegistrationResponse registerStudent(@RequestBody Student student){
        student.setPassword(encryptor.encrypt(student.getPassword()));
        RegistrationResponse response = registerService.registerStudent(student);
        return response;
    }

    @PostMapping(path = "/tutor")
    @ResponseBody
    public RegistrationResponse registerTutor(@RequestBody Tutor tutor){
        tutor.setPassword(encryptor.encrypt(tutor.getPassword()));
        RegistrationResponse response = registerService.registerTutor(tutor);
        return response;
    }

    @RequestMapping(value = "/student/studentid/{studentid}/activation/{activationcode}/")
    public String activateStudent(@PathVariable int studentid, @PathVariable String activationcode) {
        String response = registerService.activateStudent(studentid, activationcode);
        return response;
    }

    @RequestMapping(value = "/tutor/tutorid/{tutorid}/activation/{activationcode}/")
    public String activateTutor(@PathVariable int tutorid, @PathVariable String activationcode) {
        String response = registerService.activateTutor(tutorid, activationcode);
        return response;
    }
}
