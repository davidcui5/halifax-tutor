package group12.registration;

import group12.data_access.Student;
import group12.data_access.Tutor;

public class RegistrationServiceMock implements IRegister {
    @Override
    public RegistrationResponse registerStudent(Student student) {
        return new RegistrationResponse("SUCCESS","registered");
    }

    @Override
    public RegistrationResponse registerTutor(Tutor tutor) {
        return new RegistrationResponse("SUCCESS","registered");
    }

    @Override
    public String activateStudent(int studentID, String activationCode) {
        return "activated";
    }

    @Override
    public String activateTutor(int tutorID, String activationCode) {
        return "activated";
    }
}
