package group12.student_setting;

public interface IStudentSetting {
    String authorizeStudent(String token);

    String resendActivateCode(String token);

    String checkActivationStatus(String token);

    String changePhone(String token, String phone);

    String changeEmail(String token, String email);

    String changePassword(String token, String password);
}
