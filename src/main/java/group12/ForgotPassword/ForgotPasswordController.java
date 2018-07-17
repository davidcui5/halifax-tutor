package group12.ForgotPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ForgotPasswordController {

    @Autowired
    private IForgotPassword iForgotPassword;

    @PostMapping(path = "/studentforgotpassword")
    public @ResponseBody
    ForgotPasswordResponse sendStudentVerificationMail(@RequestBody ForgotPasswordForm student){
        ForgotPasswordResponse response = iForgotPassword.verifyStudent(student);
        return response;
    }

    @PostMapping(path = "/tutorforgotpassword")
    public @ResponseBody
    ForgotPasswordResponse sendTutorVerificationMail(@RequestBody ForgotPasswordForm tutor){
        ForgotPasswordResponse response = iForgotPassword.verifyTutor(tutor);
        return response;
    }

    @RequestMapping(value = "/student/studentid/{studentID}/email/{email}/verification/{activationCode}/", method = GET)
    public String verifyStudent(@PathVariable int studentID, @PathVariable String email,@PathVariable String activationCode) {
        System.out.println("Student ID : " + studentID + " Activation Code: " + activationCode);
        iForgotPassword.activateStudent( studentID, email, activationCode);
        return "redirect:/html/set-new-password.html?usertype=student&email=" + email;
    }

    @RequestMapping(value = "/tutor/tutorid/{tutorID}/email/{email}/verification/{activationCode}/", method = GET)
    public String verifyTutor(@PathVariable int tutorID,  @PathVariable String email, @PathVariable String activationCode) {
        System.out.println("Tutor ID : " + tutorID + " Activation Code: " + activationCode);
        iForgotPassword.activateTutor( tutorID,  email,  activationCode);
        return "redirect:/html/set-new-password.html?usertype=tutor&email=" + email;
    }

    @PostMapping(path = "/setnewpasswordstudent")
    public @ResponseBody
    ForgotPasswordResponse updateStudentPassword(@RequestBody ForgotPasswordForm student){
        ForgotPasswordResponse response = iForgotPassword.setNewPasswordStudent(student);
        return response;
    }

    @PostMapping(path = "/setnewpasswordtutor")
    public @ResponseBody
    ForgotPasswordResponse updateTutorPassword(@RequestBody ForgotPasswordForm tutor){
        ForgotPasswordResponse response = iForgotPassword.setNewPasswordTutor(tutor);
        return response;
    }


}
