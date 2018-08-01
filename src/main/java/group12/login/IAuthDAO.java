package group12.login;

import group12.data_access.*;

public interface IAuthDAO {
    Student getStudentByEmail(String email);
    Tutor getTutorByEmail(String email);
    Admin getAdminByEmail(String email);
}
