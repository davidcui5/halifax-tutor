package group12.forgotpassword;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForgotPasswordTest {

    ForgotPasswordService forgotPasswordService = new ForgotPasswordService();


    ForgotPasswordForm forgotStudentPasswordForm = new ForgotPasswordForm();
    ForgotPasswordForm forgotTutorPasswordForm = new ForgotPasswordForm();

    MockForgotPasswordData mockDbda;
    MockMailer mailer;

    @Before
    public void testSetup() {

        mockDbda = new MockForgotPasswordData();
        mailer = new MockMailer();

        forgotPasswordService.setDb(mockDbda);
        forgotPasswordService.setMailer(mailer);
    }

    @Test
    public void testPositiveVerifyStudent(){
        String result = "Success";
        forgotStudentPasswordForm.setEmail("rh318779@dal.ca");
        assertEquals(result, forgotPasswordService.verifyStudent(forgotStudentPasswordForm).getResult());
    }

    @Test
    public void testNegativeVerifyStudent(){
        String result = "Failure";
        forgotStudentPasswordForm.setEmail("mailNotInDatabase@dal.ca");
        assertEquals(result, forgotPasswordService.verifyStudent(forgotStudentPasswordForm).getResult());
    }

    @Test
    public void testPositiveVerifyTutor(){
        String result = "Success";
        forgotTutorPasswordForm.setEmail("rahulvala1502@gmail.com");
        assertEquals(result, forgotPasswordService.verifyTutor(forgotTutorPasswordForm).getResult());
    }

    @Test
    public void testNegativeVerifyTutor(){
        String result = "Failure";
        forgotTutorPasswordForm.setEmail("mailNotInDatabase@dal.ca");
        assertEquals(result, forgotPasswordService.verifyTutor(forgotTutorPasswordForm).getResult());
    }

    @Test
    public void testActivateStudent(){
        int studentId = 905;
        String email = "rh318779@dal.ca";
        String activationCode = "b530539f-b3d1-4829-982e-5087871467b4";
        String result = "redirect:/html/set-new-password.html?usertype=student&email=" + "rh318779@dal.ca";

        assertEquals(result, forgotPasswordService.activateStudent(studentId, email, activationCode));
    }

    @Test
    public void testActivateTutor(){
        int tutorId = 437;
        String email = "rahulvala1502@gmail.com";
        String activationCode = "b530539f-b3d1-4829-982e-5087871467b4";
        String result = "redirect:/html/set-new-password.html?usertype=tutor&email=" + "rahulvala1502@gmail.com";

        assertEquals(result, forgotPasswordService.activateTutor(tutorId, email, activationCode));
    }

    @Test
    public void testPositiveSetNewPasswordStudent(){
        String result = "Success";
        forgotStudentPasswordForm.setEmail("rh318779@dal.ca");
        forgotStudentPasswordForm.setPassword("Qwert123");
        assertEquals(result, forgotPasswordService.setNewPasswordStudent(forgotStudentPasswordForm).getResult());
    }

    @Test
    public void testNegativeSetNewPasswordStudent(){
        String result = "Failure";
        forgotStudentPasswordForm.setEmail("mailNotInDatabase@dal.ca");
        forgotStudentPasswordForm.setPassword("Qwert123");
        assertEquals(result, forgotPasswordService.setNewPasswordStudent(forgotStudentPasswordForm).getResult());
    }

    @Test
    public void testPositiveSetNewPasswordTutor(){
        String result = "Success";
        forgotTutorPasswordForm.setEmail("rahulvala1502@gmail.com");
        forgotTutorPasswordForm.setPassword("Qwert123");
        assertEquals(result, forgotPasswordService.setNewPasswordTutor(forgotTutorPasswordForm).getResult());
    }

    @Test
    public void testNegativeSetNewPasswordTutor(){
        String result = "Failure";
        forgotTutorPasswordForm.setEmail("mailNotInDatabase@dal.ca");
        forgotTutorPasswordForm.setPassword("Qwert123");
        assertEquals(result, forgotPasswordService.setNewPasswordTutor(forgotTutorPasswordForm).getResult());
    }


}
