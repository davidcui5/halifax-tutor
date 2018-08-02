package group12.studentsetting;

public interface IStudentSetting {
    String authorizeStudent(String token) throws Exception;

    String resendActivateCode(String token) throws Exception;

    String checkActivationStatus(String token) throws Exception;

    String changePhone(String token, String phone) throws Exception;

    String changeEmail(String token, String email) throws Exception;

    String changePassword(String token, String password) throws Exception;
}
