package group12.forgotpassword;

public interface IForgotPassword {

    ForgotPasswordResponse verifyStudent(ForgotPasswordForm student);

    ForgotPasswordResponse verifyTutor(ForgotPasswordForm tutor);

    ForgotPasswordResponse setNewPasswordStudent(ForgotPasswordForm student);

    ForgotPasswordResponse setNewPasswordTutor(ForgotPasswordForm tutor);

    String activateStudent(int studentID, String email, String activationCode);

    String activateTutor(int tutorID, String email, String activationCode);


}
