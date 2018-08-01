package group12.login;

import group12.dataaccess.*;

public interface IAuthDAO {
    Student getStudentByEmail(String email);
    Tutor getTutorByEmail(String email);
    Admin getAdminByEmail(String email);
}
