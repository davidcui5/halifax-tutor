package group12.login;

public interface IAuthDAO {

    UserDTO getStudentByEmail(String email);
    UserDTO getTutorByEmail(String email);
    UserDTO getAdminByEmail(String email);
/*
    boolean isStudentEmailNotFound(String email);
    String getStudentPassword(String email);
    boolean isStudentNotActivated(String email);
    boolean isStudentBanned(String email);

    boolean isTutorEmailNotFound(String email);
    String getTutorPassword(String email);
    boolean isTutorNotActivated(String email);
    boolean isTutorBanned(String email);

    boolean isAdminEmailNotFound(String email);
    String getAdminPassword(String email);*/
}
