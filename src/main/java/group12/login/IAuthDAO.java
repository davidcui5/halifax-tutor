package group12.login;

public interface IAuthDAO {

    boolean isStudentEmailWrong(String email);
    String getStudentPassword(String email);
    boolean isStudentNotActivated(String email);
    boolean isStudentBanned(String email);

    boolean isTutorEmailWrong(String email);
    String getTutorPassword(String email);
    boolean isTutorNotActivated(String email);
    boolean isTutorBanned(String email);

    boolean isAdminEmailWrong(String email);
    String getAdminPassword(String email);
}
