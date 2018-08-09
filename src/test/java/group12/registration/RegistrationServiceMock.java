package group12.registration;

import group12.dataaccess.Student;
import group12.dataaccess.Tutor;

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
