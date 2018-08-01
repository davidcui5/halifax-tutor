package group12.registration;

import group12.data_access.Student;
import group12.data_access.Tutor;

public interface IRegister {

    RegistrationResponse registerStudent(Student student);

    RegistrationResponse registerTutor(Tutor tutor);

    String activateStudent(int studentID, String activationCode);

    String activateTutor(int tutorID, String activationCode);

}
