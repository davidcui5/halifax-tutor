package group12.ForgotPassword;

import group12.DBDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class ForgotPasswordController {

    @Autowired
    private DBDAO db = new DBDAO();

    @Autowired
    private MailMail m = new MailMail();


    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;

    @PostMapping(path = "/studentforgotpassword")
    public @ResponseBody
    String sendStudentVerificationMail(@RequestBody Student student){
//        if (db.isEmailNew(student.getEmail())) {
        int studentID = db.getStudentId(student.getEmail());
        UUID uuid = UUID.randomUUID();
        db.saveActivationCode(uuid.toString());
        serverURL = "http://localhost:8080";
        m.sendMail(emailSender, student.getEmail(), "Verification",
                "Verification " + serverURL + "/student/studentid/" + studentID + "/email/" + student.getEmail() + "/verification/" + uuid.toString() + "/");
        return "verification email sent";
//        }
//        else{
//            return "Student not found";
//        }
    }

    @PostMapping(path = "/tutorforgotpassword")
    public @ResponseBody
    String sendTutorVerificationMail(@RequestBody Tutor tutor){
//        if (db.isEmailNew(tutor.getEmail())) {
        int tutorID = db.getTutorID(tutor.getEmail());
        UUID uuid = UUID.randomUUID();
        db.saveActivationCode(uuid.toString());
        serverURL = "http://localhost:8080";
        m.sendMail(emailSender, tutor.getEmail(), "Verification",
                "Verification " + serverURL + "/tutor/tutorid/" + tutorID + "/email/" + tutor.getEmail() + "/verification/" + uuid.toString() + "/");
        return "verification email sent";
//        }
//        else{
//            return "Tutor not found";
//        }
    }

    @RequestMapping(value = "/student/studentid/{studentid}/email/{email}/verification/{activationcode}/", method = GET)
    public String verifyStudent(@PathVariable int studentid, @PathVariable String email, @PathVariable String activationcode) {
        System.out.println("Student ID : " + studentid + " Activation Code: " + activationcode);
        db.activateStudent(studentid, activationcode);
        return "redirect:/html/set-new-password.html?usertype=student&email=" + email;
    }

    @RequestMapping(value = "/tutor/tutorid/{tutorid}/email/{email}/verification/{activationcode}/", method = GET)
    public String verifyTutor(@PathVariable int tutorid,  @PathVariable String email, @PathVariable String activationcode) {
        System.out.println("Tutor ID : " + tutorid + " Activation Code: " + activationcode);
        db.activateTutor(tutorid, activationcode);
        return "redirect:/html/set-new-password.html?usertype=tutor&email=" + email;
    }


    @PostMapping(path = "/setnewpasswordstudent")
    public @ResponseBody
    String updateStudentPassword(@RequestBody Student student){
//        if (db.isEmailNew(student.getEmail())) {
        db.setNewPasswordStudent(student.getEmail(), student.getPassword());
        return "password update success";
//        }
//        else{
//            return "Student not found";
//        }
    }

    @PostMapping(path = "/setnewpasswordtutor")
    public @ResponseBody
    String updateTutorPassword(@RequestBody Tutor tutor){
//        if (db.isEmailNew(tutor.getEmail())) {
        db.setNewPasswordTutor(tutor.getEmail(),tutor.getPassword());
        return "password update success";
//        }
//        else{
//            return "Tutor not found";
//        }
    }
}
