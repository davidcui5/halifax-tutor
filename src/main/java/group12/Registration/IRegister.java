package group12.Registration;

public interface IRegister {

    RegistrationResponse registerStudent(Student student);

    RegistrationResponse registerTutor(Tutor tutor);

    String activateStudent(int studentID, String activationCode);

    String activateTutor(int tutorID, String activationCode);

}
