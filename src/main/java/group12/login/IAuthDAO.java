package group12.login;

public interface IAuthDAO {

    UserDTO getStudentByEmail(String email);
    UserDTO getTutorByEmail(String email);
    UserDTO getAdminByEmail(String email);

}
